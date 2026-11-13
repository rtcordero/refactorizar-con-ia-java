#!/usr/bin/env bash
# Recorre el historial y comprueba que cada paso hace lo que dice su mensaje:
# los commits «(rojo)» tienen que fallar y los «(verde)», pasar. Los demás se saltan.
set -euo pipefail

raiz=$(git rev-parse --show-toplevel)
copia="$(git rev-parse --absolute-git-dir)/comprobar-historial"
export MAVEN_CACHE="${MAVEN_CACHE:-$raiz/.cache-maven}"
errores=0

# Limpia una copia que haya quedado de una ejecución interrumpida.
git -C "$raiz" worktree remove --force "$copia" 2>/dev/null || true
rm -rf "$copia"
git -C "$raiz" worktree prune
git -C "$raiz" worktree add --quiet --detach "$copia" HEAD
trap 'git -C "$raiz" worktree remove --force "$copia"' EXIT

for commit in $(git -C "$raiz" rev-list --reverse HEAD); do
  asunto=$(git -C "$raiz" log -1 --format=%s "$commit")
  case "$asunto" in
    *'(rojo)') esperado=falla ;;
    *'(verde)') esperado=pasa ;;
    *) continue ;;
  esac
  git -C "$copia" checkout --quiet "$commit"
  if (cd "$copia" && "$raiz/scripts/mvn.sh" clean test >/dev/null 2>&1); then obtenido=pasa; else obtenido=falla; fi
  if [ "$obtenido" = "$esperado" ]; then
    echo "ok    ${commit:0:7} $asunto"
  else
    echo "MAL   ${commit:0:7} $asunto: se esperaba que $esperado y $obtenido"
    errores=$((errores + 1))
  fi
done

if [ "$errores" -gt 0 ]; then
  echo "$errores commits no hacen lo que dice su mensaje"
  exit 1
fi

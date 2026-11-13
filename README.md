# Refactorizar con IA en pasos pequeños

Código del artículo [Refactorizar con IA en pasos pequeños, sin romper nada](https://rubentejera.com/refactorizar-con-ia/). Continúa el de [Código heredado con IA: caracteriza antes de cambiar](https://rubentejera.com/tests-de-caracterizacion-codigo-legado-ia/) y su repositorio, `codigo-heredado-con-ia-java`.

Parte de la tarifa enredada de un aparcamiento en Java, ya con sus quince tests de caracterización, y llega al mismo diseño que allí se hizo de un salto (80 líneas cambiadas en un commit), pero en **doce pasos**. Cada paso es una refactorización con nombre, cambia una sola cosa y termina con los tests en verde.

| Paso | Líneas (+/−) | Encargo (resumido) | Refactorización |
| --- | --- | --- | --- |
| 1 | 21 / 21 | Pon nombres que se entiendan a las variables | *Rename Variable* |
| 2 | 21 / 22 | Quita el `else` que sigue al `return` | *Replace Nested Conditional with Guard Clauses* |
| 3 | 1 / 1 | Baja la declaración del importe por debajo de la guarda | *Slide Statements* |
| 4 | 9 / 11 | Aplana el `else { if }` de los tipos en una cadena de `else if` | (sin nombre en el catálogo) |
| 5 | 6 / 3 | Da nombre al 15 y al 60 | *Replace Magic Literal* |
| 6 | 9 / 4 | Saca a una función el cálculo de las horas | *Extract Function* |
| 7 | 1 / 5 | Cambia ese cálculo por `Math.ceilDiv` | *Substitute Algorithm* |
| 8 | 27 / 0 | Crea el enum `Vehiculo` con sus datos, aún sin usarlo | *Parameterize Function* |
| 9 | 1 / 7 | Haz que la rama del coche use el enum | *Replace Inline Code with Function Call* |
| 10 | 2 / 8 | Lo mismo con la furgoneta y la moto | *Replace Inline Code with Function Call* |
| 11 | 13 / 10 | Sustituye la cadena de `if` por buscar el tipo en el enum | *Replace Primitive with Object* |
| 12 | 1 / 1 | Pasa el comentario a la documentación de la clase | — |

La rama `paso-en-rojo` guarda un intento fallido del paso 7, `minutos / 60 + 1`, que rompe seis tests: es el ejemplo de por qué un paso en rojo se deshace en lugar de arreglarse encima.

La CI recorre el historial y comprueba que cada commit acaba como dice su mensaje: `(verde)` si los tests pasan y `(rojo)` si fallan.

## Cómo ejecutarlo

Necesitas JDK 25 o 27 y Maven 3.10.0, o solo Docker.

```bash
mvn test                        # con Maven instalado
scripts/mvn.sh test             # o con Docker y la imagen oficial de Maven
scripts/comprobar-historial.sh  # cada paso del historial, uno a uno
```

Para recorrer los pasos:

```bash
git log --oneline --reverse
git show <commit>   # el cambio de cada paso
```

## Cómo se ha hecho

Desarrollado con el enfoque [IA Limpia](https://rubentejera.com/que-es-la-ia-limpia/): un agente de IA (Claude Code 2.1.323, con Claude Opus 5.5) hizo los pasos en una sesión, uno cada vez y con el código final ya decidido; los tests validan cada paso antes del commit y el resultado se revisa y se aprueba a mano antes de publicarlo. El intento en rojo se hizo a propósito.

## Licencia

MIT. Ver `LICENSE`.

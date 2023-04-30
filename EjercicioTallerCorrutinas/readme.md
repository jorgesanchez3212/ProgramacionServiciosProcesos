## Ejercicio Taller Corrutinas
Tenemos 3 mecanicos y cada mecanico arregla entre 4 y 8 coches.
El coche como atributos tiene la reparacion(100 a 300) y el precio (50 a 200)
Saber la recaudación total que hemos obtenido.

## SOLUCIÓN
Hemos utilizado la función async() para lanzar cada corutina ejecutando el metodo de reparar coches 
de forma asíncrona, y hemos almacenado los resultados en objetos Deferred. 

(Los objetos Deferred que son lo que devuelve los async() son una clase que representa un valor que estará disponible en 
algún momento en el futuro. Es similar a la promesa)

Luego, hemos utilizado la función awaitAll() para esperar a que todas 
las coroutines se completen y obtener los resultados de cada reparación de coche.
Al final, hemos calculado la recaudación total sumando los ingresos de cada reparación de coche. 

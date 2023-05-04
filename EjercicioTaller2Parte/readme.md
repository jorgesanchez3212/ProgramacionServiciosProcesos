## EJERCICIO TALLER 2 PARTE

Tenemos un taller, que solo acepta 5 coches, cada coche es atendido en un tiempo de 300 a 600 milisegundos. Hay dos puertas
de entrada y tenemos tres puertas de salida (Los mecanicos). Se procesan 60 coches, pero no sabemos cuantos entran por cada
entrada.
Tiene que ser equitativo y justo o sea con prioridad, si un coche lleva esperando mucho, entra primero ese.

## SOLUCIÓN

Es un problema de Productor-Consumidor en el cual los productores son las puertas de entrada y los consumidores los mecanicos.

Para que sea equitativo y justo he decidido utilizar un Reentrant Lock, 

Para garantizar que el procesamiento de los coches sea equitativo y justo, 
podrías utilizar un PriorityBlockingQueue para almacenar los coches que esperan a ser atendidos. 
Este tipo de cola permite que los elementos sean ordenados por prioridad, 
de acuerdo a un comparador que se le proporciona al momento de crear la cola. 
En este caso, podrías utilizar el tiempo de espera de cada coche como factor de prioridad.

En cuanto al uso de contextos, podrías utilizar el contexto Dispatchers.Default para ejecutar las tareas de procesamiento 
de los coches en un hilo separado del hilo principal, y así no bloquear la interfaz de usuario. 

En cuanto al uso de ReentrantLock, podrías utilizarlo para garantizar el acceso exclusivo a los recursos compartidos entre hilos, 
como por ejemplo las puertas de entrada y salida del taller. De esta forma, evitas que varios hilos intenten acceder 
simultáneamente a los mismos recursos y puedan producir errores o condiciones de carrera.
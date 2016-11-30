Ejercicio 2
-----------

El siguiente programa simula una descarga al apretar un botón. Para no bloquear el UI Thread como en el ejemplo anterior hemos usado un Runnable y un Thread, desde el que realizamos la descarga ficticia y actualizamos el botón.

Por desgracia no podemos modificar el botón desde otro thread ya que da el siguiente error:

android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.


Modifica el programa para que se utilice el framework AsyncTask y no de el error.


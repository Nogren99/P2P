# Diseño e implementación de un sistema de mensajería instantánea - P2P

GRUPO 6 - Universidad Nacional de Mar del Plata.

![h1](https://user-images.githubusercontent.com/69020112/235544911-a53bc803-e16b-4991-855e-35cd702cffc0.png)


Se construyó un sistema de mensajería P2P el cual seguía las características brindadas por la cátedra de Análisis y Diseño de Sistemas 2:
* Una vez iniciada una sesión entre dos usuarios, los mismos pueden intercambiar toda la cantidad de mensajes que quieran hasta que uno de los dos participantes cierre la sesión.
* Para iniciar un diálogo, el receptor debe estar en modo escucha. Es decir, a la espera de que otro usuario quiera establecer el diálogo con el.
* Solo se permite una única sesión en simultáneo. Un usuario no puede tener más de un diálogo a la vez.
* Para comenzar una sesión, el usuario iniciador debe ingresar la dirección IP y puerto que corresponde al usuario con el que quiere establecer el diálogo.
* El sistema respetará el estilo de arquitectura Peer to Peer.

# Arquitectura
Se optó por utilizar una arquitectura MVC (modelo, vista, controlador) la cual permitió una separación en tres componentes. Los datos, la metodología y la interfaz gráfica de la aplicación. Algunas de las ventajas que brindó esta arquitectura fueron:
* Separación clara de dónde tiene que ir cada tipo de lógica, facilitando el mantenimiento y la escalabilidad de nuestra aplicación.
* Sencillez para crear distintas representaciones de los mismos datos.
* Reutilización de los componentes.

# ¿Como se puede ejecutar?
Descargue los archivos ejecutables .jar. Una vez descargado debe darle doble click para poder abrirlo.





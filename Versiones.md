PARA ACCEDER A LA DOCUMENTACION DEL PROYECTO
-> localhost:8080/documentacion/

Versiones:

0.0.1: Version inicial:
	
			 Proyecto vacio

0.0.2: Nuevo:

			Entidades Evento, Usuario
			Organizacion del proyecto
			Repositorios de Evento, Usuario
			Configuracion para conexion a base de datos MySQL
			Configuracion para aplicar documentacion en Swagger 3
			Archivo de versiones
			
0.0.3: Nuevo:

			Servicios para Eventos y usuarios
			Mejorada la organizacion del proyecto
			Agregados getters / setters para Evento
			Agregadas querys personalizadas para Usuarios y Eventos (en el repositorio se encuentran)
			
0.0.4: Nuevo:

			Controlador para Usuario con sus respectivos metodos de A/B/M y LogIn (este ultimo debe probarse si o si en Postman)
			Documentacion mejorada
			
0.0.5: Nuevo:

			Controlador para Eventos, con sus respectivos metodos de A/B/M
			Correccion de errores varios
			
0.0.6: Correccion:
			
			Cambiado endpoint "LogIn" GET -> POST (mitigado el error que causaba al probarlo en Swagger)
			Cambiado y organizado las direcciones de cada endpoint de usuarios y eventos
			Cambiado el titulo de la documentacion en Swagger	

0.0.6-1: Nuevo:

			Agregado Spring Security, con Basic authentication usando "admin//admin"
			
0.0.6-2: Nuevo:
		
			Agregado autenticacion/autorizacion con JWT
			Quitado: Usuario admin//admin			
			
0.0.7: Correccion:

			Corregido errores con JWT

0.0.8: Nuevo:

			Añadido archivo .bat para crear la base de datos (usuario root, contraseña root, puerto 3306)
			Para crear las tablas, ejecutar el proyecto
			Para probar autenticacion / autorizacion utilizar PostMan, opcion "Bearer token" una vez obtenido del 			metodo "/usuarios/login/"
			
0.0.9: Correccion:

			Path de creacion de usuarios cambiada a "localhost:8080/usuarios/alta/" ya que RetroFit pide "/" al 			final de cada URL
			
0.0.10: Correccion:

			Path de login de usuarios cambiada a "localhost:8080/usuarios/login/" ya que RetroFit pide "/" al 			final de cada URL
			
0.0.11: Nuevo:

			Agregado metodo para desencriptar la contraseña enviada por el front.
			Agregado metodo para desencriptar el nombre enviado por el front.
			
0.0.12: Correccion:

			Cambiado el metodo para cambiar contraseña para aceptar datos encriptados.
			
0.0.13: Correccion:

			Cambiado el metodo de listado de listado general de eventos.
			
0.0.14: Nuevo:

			Creado metodo para devolver datos de un evento en controlador de eventos.
PARA ACCEDER A LA DOCUMENTACION DEL PROYECTO
-> localhost:8080/

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
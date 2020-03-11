Para que el programa funcione:
sudo docker start c8583de41b41
sudo docker exec -it c8583de41b41

Correr apache tomcat

Para poner el war en el tomcat copiar archivo war ubicado en target en la carpeta del proyecto a ~/Descargas/apache-tomcat-8.5.51/webapps/

PRUEBAS EN POSTMAN

URL: http://localhost:8080/hudsons/webresources/burgers

MTODOS:                    ENTRADA
	POST Y PUT         { "id": 9 ,"name": "", "price": 19.29}
	

	GET
	GET Y DELETE		http://localhost:8080/hudsons/webresources/burgers/2
	

# Kinalapp-2022107
Kinalapp es un sistema de gestión de ventas diseñado para pequeños y medianos negocios. Permite administrar clientes, productos, usuarios y registrar ventas de manera sencilla y eficiente.

Con esta aplicación, usted podrá:
- Mantener un registro organizado de sus clientes
- Administrar el inventario de productos
- Controlar el stock disponible
- Registrar ventas de forma rápida
- Gestionar usuarios que operan el sistema
- Obtener el total de ventas automáticamente

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 4.0.2**
* **Maven** (Gestor de dependencias)
* **MySQL** (Sistema Gestor de Base de datos)
* **GIT** (Para Clonar el repositorio.)

## Requisitos Previos
Antes de ejecutar la aplicación, debe de tener instalado:
* JDK 17 o superior
* Maven Instalado
* Una instancia activa en MySQL

## Instalacion y Ejecución
1.Instalar Java 21 desde oracle.com

2.Instalar MySQL desde mysql.com

3.Instalar IntelliJ IDEA desde jetbrains.com

4.Instalar Postman desde postman.com

5.Abrir MySQL y ejecutar: CREATE DATABASE dbClientes_in5am;

6.Clonar el repositorio desde GitHub

7.Abrir el proyecto en IntelliJ IDEA

8.Abrir el archivo src/main/resources/application.properties

9.Cambiar la contraseña de MySQL (reemplazar TU_CONTRASEÑA)

10.Iniciar el servicio de MySQL en tu computadora

11.Ejecutar la aplicación (botón Run verde en IntelliJ)

12.Esperar a que aparezca "Started KinalApplication" en consola

13.Abrir Postman

14.Crear un cliente: POST a http://localhost:9000/clientes

15.Crear un producto: POST a http://localhost:9000/productos

16.Crear un usuario: POST a http://localhost:9000/usuarios

17.Crear una venta: POST a http://localhost:9000/ventas

18.Agregar producto a venta: POST a http://localhost:9000/detalles-venta

19.Verificar stock: GET a http://localhost:9000/productos/1

20.Verificar venta: GET a http://localhost:9000/ventas/1

## Pruebas y Evidencias
## Evidencias de Pruebas

![Crear Cliente](screenshots/Captura%20de%20pantalla%202026-03-27%20224505.png)

![Listar Clientes](screenshots/Captura%20de%20pantalla%202026-03-27%20224515.png)

![Crear Producto](screenshots/Captura%20de%20pantalla%202026-03-27%20224531.png)

![Crear Usuario](screenshots/Captura%20de%20pantalla%202026-03-27%20224544.png)

![Crear Venta](screenshots/Captura%20de%20pantalla%202026-03-27%20224601.png)

![Agregar Detalle](screenshots/Captura%20de%20pantalla%202026-03-27%20224616.png)

![Stock Actualizado](screenshots/Captura%20de%20pantalla%202026-03-27%20224627.png)

![Total Venta](screenshots/Captura%20de%20pantalla%202026-03-27%20224637.png)
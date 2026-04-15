# KinalApp - Sistema de Gestión de Ventas

## Descripción
 
Kinalapp es un sistema de gestión de ventas diseñado para pequeños y medianos negocios. Permite administrar clientes, productos, usuarios y registrar ventas de manera sencilla y eficiente.

## Funcionalidades

Con esta aplicación, usted podra:

- Mantener un registro organizado de sus clientes
- Administrar el inventario de productos
- Controlar el stock disponible
- Registrar ventas de forma rapida
- Gestionar usuarios que operan el sistema
- Obtener el total de ventas automaticamente

## Tecnologias Utilizadas

- Java 21
- Spring Boot 4.0.2
- Maven (Gestor de dependencias)
- MySQL (Sistema Gestor de Base de datos)
- Git (Para clonar el repositorio)
- Thymeleaf (Para las vistas web)
- H2 Database (Base de datos para pruebas)

---

## Requisitos Previos

Antes de ejecutar la aplicacion, debe tener instalado lo siguiente:

### 1. Java 21

Java es el lenguaje en el que esta hecha la aplicacion. Sin el, la aplicacion no puede funcionar.

**Como instalar Java 21:**
1. Abra su navegador web (Chrome, Edge o Firefox)
2. Vaya a la pagina oficial de Oracle: https://www.oracle.com/java/technologies/downloads/#java21
3. Descargue el instalador para Windows (archivo .exe)
4. Haga doble clic en el archivo descargado
5. Siga los pasos del asistente de instalacion (siempre haga clic en "Siguiente" o "Next")
6. Al finalizar, haga clic en "Cerrar" o "Close"

### 2. MySQL

MySQL es la base de datos donde se guardara toda la informacion de su negocio.

**Como instalar MySQL:**
1. Abra su navegador web
2. Vaya a: https://dev.mysql.com/downloads/installer/
3. Descargue el archivo "MySQL Installer for Windows"
4. Haga doble clic en el archivo descargado
5. Seleccione "Developer Default" y haga clic en "Next"
6. Haga clic en "Execute" para instalar los componentes
7. Cuando termine, haga clic en "Next"
8. En la pantalla de configuracion, deje las opciones por defecto y haga clic en "Next"
9. Establezca una contraseña para el usuario "root" (ESCRIBALA EN UN LUGAR SEGURO, la necesitara mas adelante)
10. Haga clic en "Next" hasta finalizar la instalacion

### 3. Git

Git es la herramienta que permite descargar el codigo de la aplicacion.

**Como instalar Git:**
1. Abra su navegador web
2. Vaya a: https://git-scm.com/download/win
3. La descarga comenzara automaticamente
4. Haga doble clic en el archivo descargado
5. Siga los pasos del asistente (siempre haga clic en "Siguiente" o "Next")
6. Al finalizar, haga clic en "Finish"

### 4. IntelliJ IDEA (Opcional pero recomendado)

IntelliJ IDEA es un programa que facilita la ejecucion de la aplicacion.

**Como instalar IntelliJ IDEA:**
1. Abra su navegador web
2. Vaya a: https://www.jetbrains.com/idea/download/
3. Descargue la version "Community" (es gratuita)
4. Haga doble clic en el archivo descargado
5. Siga los pasos del asistente de instalacion
6. Cuando termine, abra el programa

---

## Instalacion y Ejecucion - Paso a Paso

### Parte 1: Preparar la Base de Datos

**Paso 1: Abrir MySQL**
1. Busque "MySQL Command Line Client" en el menu de inicio de Windows
2. Haga clic para abrirlo
3. Cuando pida contraseña, escriba la que configuro durante la instalacion de MySQL

**Paso 2: Crear la base de datos**
1. Dentro de MySQL, escriba el siguiente comando:
CREATE DATABASE dbClientes_in5am;
2. Presione la tecla "Enter" de su teclado
3. Verificara un mensaje que dice "Query OK, 1 row affected"

**Paso 3: Verificar que se creo correctamente**
SHOW DATABASES;
Debera aparecer "dbClientes_in5am" en la lista.

**Paso 4: Cerrar MySQL**
Escriba "exit" y presione Enter

### Parte 2: Descargar el Proyecto

**Paso 1: Abrir Git Bash**
1. Busque "Git Bash" en el menu de inicio
2. Haga clic para abrirlo (aparecera una ventana negra)

**Paso 2: Navegar a la carpeta donde quiere guardar el proyecto**
Escriba el siguiente comando y presione Enter:
cd C:/

**Paso 3: Clonar (descargar) el repositorio**
Escriba el siguiente comando y presione Enter:
git clone https://github.com/su-usuario/kinalapp.git
(Reemplace "su-usuario" con el usuario real del repositorio)

**Paso 4: Verificar que se descargo**
Escriba:
dir
Debera aparecer una carpeta llamada "kinalapp"

### Parte 3: Configurar el Proyecto

**Paso 1: Abrir IntelliJ IDEA**
1. Busque "IntelliJ IDEA" en el menu de inicio
2. Haga clic para abrirlo

**Paso 2: Abrir el proyecto**
1. En la pantalla de bienvenida, haga clic en "Open"
2. Busque la carpeta "kinalapp" que descargo antes
3. Seleccione la carpeta y haga clic en "OK"

**Paso 3: Esperar a que cargue**
IntelliJ tardara unos minutos en cargar el proyecto. Ver una barra de progreso en la parte inferior.

**Paso 4: Abrir el archivo de configuracion**
1. En el panel izquierdo, busque la carpeta "src"
2. Haga clic para expandirla
3. Busque "main" -> "resources"
4. Haga doble clic en el archivo "application.properties"

**Paso 5: Configurar la contraseña de MySQL**
Busque la linea que dice:
spring.datasource.password=_odmon5Am

Cambiela por su contraseña real de MySQL. Por ejemplo, si su contraseña es "123456", la linea quedara asi:
spring.datasource.password=123456

**Paso 6: Guardar el archivo**
Presione "Ctrl + S" en su teclado para guardar los cambios

### Parte 4: Ejecutar la Aplicacion

**Paso 1: Iniciar MySQL**
1. Busque "Services" en el menu de inicio de Windows
2. Busque el servicio llamado "MySQL" o "MySQL80"
3. Si dice "Stopped", haga clic derecho y seleccione "Start"

**Paso 2: Ejecutar la aplicacion desde IntelliJ**
1. En IntelliJ, busque la flecha verde en la parte superior derecha
2. Haga clic en esa flecha (es el boton "Run")
3. O tambien puede presionar "Shift + F10" en su teclado

**Paso 3: Esperar a que inicie**
En la parte inferior de IntelliJ aparecera una consola con texto. Espere hasta que aparezca el mensaje:

Started KinalApplication in X seconds
Tomcat started on port 9000 (http)

Eso significa que la aplicacion ya esta funcionando.

---

## Como Abrir la Aplicacion en el Navegador

### Paso 1: Abrir su navegador web
Abra Google Chrome, Microsoft Edge o Mozilla Firefox (cualquiera funciona)

### Paso 2: Escribir la direccion de la aplicacion
En la barra de direcciones (donde normalmente escribe paginas web como www.google.com), escriba exactamente:

http://localhost:9000/login

Luego presione la tecla "Enter" de su teclado

### Paso 3: Ver la pantalla de login
Aparecera una pantalla con dos pestañas:
- Iniciar Sesion (para usuarios que ya tienen cuenta)
- Crear Cuenta (para registrarse por primera vez)

---

## Como Crear una Cuenta de Usuario (Primer uso)

**Paso 1: Ir a la pestaña "Crear Cuenta"**
Haga clic en el boton que dice "Crear Cuenta"

**Paso 2: Completar el formulario de registro**
Complete los siguientes campos:

- Usuario: elija un nombre de usuario (ejemplo: admin, juanperez, maria)
- Correo Electronico: escriba su direccion de email (ejemplo: admin@kinalapp.com)
- Contraseña: elija una contraseña (debe tener al menos 6 caracteres)
- Confirmar Contraseña: escriba la misma contraseña nuevamente
- Rol: seleccione "Administrador" (esto le da todos los permisos)

**Paso 3: Crear la cuenta**
Haga clic en el boton verde que dice "Crear Cuenta"

**Paso 4: Confirmar registro**
Aparecera un mensaje verde que dice "Cuenta creada exitosamente"

---

## Como Iniciar Sesion

**Paso 1: Ir a la pestaña "Iniciar Sesion"**
Haga clic en el boton que dice "Iniciar Sesion"

**Paso 2: Ingresar sus datos**
- Usuario: escriba el usuario que creo (ejemplo: admin)
- Contraseña: escriba la contraseña que eligio

**Paso 3: Entrar al sistema**
Haga clic en el boton azul que dice "Iniciar Sesion"

**Paso 4: Ver el Dashboard (Pantalla principal)**
Despues de iniciar sesion, vera la pantalla principal del sistema con tres modulos:
- Clientes
- Productos
- Ventas

---

## Guia de Uso - Como realizar operaciones basicas

### Navegacion entre modulos
En la parte superior de la pantalla hay un menu con tres opciones:
- Clientes: para administrar sus clientes
- Productos: para administrar su inventario
- Ventas: para registrar y ver ventas

Haga clic en cada opcion para cambiar entre modulos.

### Como agregar un cliente

**Paso 1:** Haga clic en "Clientes" en el menu superior

**Paso 2:** En el formulario de la derecha, complete los campos:
- DPI: ingrese el numero de identificacion del cliente (ejemplo: 1234567890101)
- Nombre: ingrese el nombre (ejemplo: Juan)
- Apellido: ingrese el apellido (ejemplo: Perez)
- Direccion: ingrese la direccion (ejemplo: Zona 10, Ciudad de Guatemala)
- Estado: seleccione "Activo"

**Paso 3:** Haga clic en el boton "Guardar"

**Paso 4:** Verifique que el cliente aparecio en la tabla de la izquierda

### Como agregar un producto

**Paso 1:** Haga clic en "Productos" en el menu superior

**Paso 2:** En el formulario de la derecha, complete los campos:
- Nombre Producto: ingrese el nombre del producto (ejemplo: Camisa Polo)
- Precio: ingrese el precio de venta (ejemplo: 150.00)
- Stock: ingrese la cantidad disponible (ejemplo: 50)
- Estado: seleccione "Activo"

**Paso 3:** Haga clic en el boton "Guardar"

**Paso 4:** Verifique que el producto aparecio en la tabla de la izquierda

### Como registrar una venta

**Paso 1:** Haga clic en "Ventas" en el menu superior

**Paso 2:** En el formulario de la derecha, seleccione un cliente:
- Haga clic en el cuadro "Cliente"
- Seleccione un cliente de la lista desplegable

**Paso 3:** Agregar productos a la venta:
- Seleccione un producto de la lista
- Ingrese la cantidad que desea vender
- El subtotal se calcula automaticamente

**Paso 4:** Si necesita agregar mas productos:
- Haga clic en "Agregar otro producto"
- Repita el paso 3 para cada producto adicional

**Paso 5:** Verifique el total:
- El sistema calcula automaticamente el total de la venta
- Verifique que el monto sea correcto

**Paso 6:** Guardar la venta:
- Haga clic en el boton "Guardar Venta"
- Aparecera un mensaje de confirmacion

**Paso 7:** Verificar la venta en el historial:
- En la tabla de la izquierda (Historial de Ventas) aparecera la nueva venta
- El estado aparecera como "Activa"

### Como anular una venta

**Paso 1:** En el modulo de Ventas, busque la venta en la tabla de la izquierda

**Paso 2:** En la columna "Acciones", haga clic en el icono de "ban" (un circulo con una linea)

**Paso 3:** Confirme que desea anular la venta:
- Aparecera un mensaje de confirmacion
- Haga clic en "Aceptar" o "Confirmar"

**Paso 4:** Verifique el cambio:
- El estado de la venta cambiara de "Activa" a "Anulada"
- La venta ya no contara en los totales

### Como filtrar el historial de ventas

En el modulo de Ventas, en la parte superior de la tabla hay tres botones:
- Todas: muestra todas las ventas (activas y anuladas)
- Activas: muestra solo las ventas activas
- Anuladas: muestra solo las ventas anuladas

Haga clic en cada boton para filtrar la lista.

### Como editar un cliente

**Paso 1:** En el modulo de Clientes, busque el cliente en la tabla

**Paso 2:** En la columna "Acciones", haga clic en el icono de lapiz

**Paso 3:** Modifique los campos que necesite cambiar

**Paso 4:** Haga clic en "Guardar"

### Como editar un producto

**Paso 1:** En el modulo de Productos, busque el producto en la tabla

**Paso 2:** En la columna "Acciones", haga clic en el icono de lapiz

**Paso 3:** Modifique los campos que necesite cambiar (nombre, precio, stock, estado)

**Paso 4:** Haga clic en "Guardar"

### Como eliminar un cliente

**Paso 1:** En el modulo de Clientes, busque el cliente en la tabla

**Paso 2:** En la columna "Acciones", haga clic en el icono de basura

**Paso 3:** Confirme que desea eliminar:
- Aparecera un mensaje de confirmacion
- Haga clic en "Aceptar"

**Paso 4:** El cliente desaparecera de la tabla

### Como eliminar un producto

**Paso 1:** En el modulo de Productos, busque el producto en la tabla

**Paso 2:** En la columna "Acciones", haga clic en el icono de basura

**Paso 3:** Confirme que desea eliminar

**Paso 4:** El producto desaparecera de la tabla

### Como cerrar sesion

**Paso 1:** En la parte superior derecha de la pantalla, busque su nombre de usuario

**Paso 2:** Haga clic en el enlace que dice "Salir"

**Paso 3:** Sera redirigido a la pantalla de login

**Paso 4:** Para volver a entrar, debera iniciar sesion nuevamente

---

## Que significan los colores y estados

### Badges de Estado

**Verde "Activo":** 
- Para clientes: significa que el cliente esta habilitado
- Para productos: significa que el producto esta disponible para la venta
- Para ventas: significa que la venta esta vigente

**Rojo "Inactivo" o "Anulada":**
- Para clientes: significa que el cliente no esta habilitado
- Para productos: significa que el producto no esta disponible
- Para ventas: significa que la venta fue anulada

---

## Ejemplos Practicos

### Ejemplo 1: Vender un producto a un cliente

1. Cliente: Maria Lopez
2. Producto: Camisa Polo (precio Q150.00)
3. Cantidad: 2 unidades

**Pasos:**
1. Ir a modulo Ventas
2. Seleccionar cliente "Maria Lopez"
3. Seleccionar producto "Camisa Polo"
4. Ingresar cantidad "2"
5. Verificar subtotal: Q300.00
6. Verificar total: Q300.00
7. Guardar venta

**Resultado:** Stock de Camisa Polo disminuye en 2 unidades

### Ejemplo 2: Vender multiples productos

1. Cliente: Juan Perez
2. Producto 1: Camisa Polo (2 unidades a Q150.00 = Q300.00)
3. Producto 2: Pantalon (1 unidad a Q250.00 = Q250.00)

**Pasos:**
1. Ir a modulo Ventas
2. Seleccionar cliente "Juan Perez"
3. Agregar primer producto: Camisa Polo, cantidad 2
4. Haga clic en "Agregar otro producto"
5. Agregar segundo producto: Pantalon, cantidad 1
6. Verificar total: Q550.00
7. Guardar venta

---

## Solucion de Problemas Comunes

### La aplicacion no inicia y muestra error de MySQL
Problema: El mensaje dice "Access denied for user 'IN5AM'"
Solucion: Verifique que la contraseña en application.properties sea correcta

### La aplicacion inicia pero no puedo acceder a http://localhost:9000
Problema: El navegador no puede conectar
Solucion: Verifique que la aplicacion haya terminado de iniciar (debe ver el mensaje "Started KinalApplication")

### Olvide la contraseña de MySQL
Problema: No recuerdo la contraseña que configure
Solucion: Debera reinstalar MySQL o seguir el proceso de recuperacion de contraseña

### El puerto 9000 esta ocupado
Problema: El mensaje dice "Port 9000 was already in use"
Solucion: Cierre otros programas que puedan usar ese puerto o cambie el puerto en application.properties

### No puedo guardar una venta
Problema: Aparece un mensaje de error
Solucion: Verifique que haya seleccionado un cliente, al menos un producto, y que el stock sea suficiente

### No aparecen clientes en la lista de ventas
Problema: El selector de clientes esta vacio
Solucion: Primero debe agregar clientes en el modulo de Clientes

### No aparecen productos en la lista de ventas
Problema: El selector de productos esta vacio
Solucion: Primero debe agregar productos en el modulo de Productos

### El stock no se actualiza despues de una venta
Problema: La cantidad en stock sigue igual
Solucion: Verifique que la venta se guardo correctamente y que el estado es "Activa"

---

## Pruebas con Postman (Para Desarrolladores)

Si desea probar la aplicacion sin usar la interfaz web, puede usar Postman:

### Crear un Cliente
- Metodo: POST
- URL: http://localhost:9000/clientes
- Body (JSON):
{
    "DPICliente": "1234567890101",
    "nombreCliente": "Juan",
    "apellidoCliente": "Perez",
    "direccion": "Ciudad de Guatemala",
    "estado": 1
}

### Listar Clientes
- Metodo: GET
- URL: http://localhost:9000/clientes

### Crear un Producto
- Metodo: POST
- URL: http://localhost:9000/productos
- Body (JSON):
{
    "nombreProducto": "Laptop Gamer",
    "precio": 4500.00,
    "stock": 10,
    "estado": 1
}

### Crear un Usuario
- Metodo: POST
- URL: http://localhost:9000/usuarios
- Body (JSON):
{
    "username": "admin",
    "password": "admin123",
    "email": "admin@kinalapp.com",
    "rol": "ADMIN",
    "estado": 1
}

### Crear una Venta
- Metodo: POST
- URL: http://localhost:9000/ventas
- Body (JSON):
{
    "fechaVenta": "2024-01-15",
    "total": 0,
    "estado": 1,
    "cliente": {
        "DPICliente": "1234567890101"
    },
    "usuario": {
        "codigoUsuario": 1
    }
}

### Agregar Detalle a Venta
- Metodo: POST
- URL: http://localhost:9000/detalles-venta
- Body (JSON):
{
    "cantidad": 2,
    "precioUnitario": 4500.00,
    "subtotal": 9000.00,
    "venta": {
        "codigoVenta": 1
    },
    "producto": {
        "codigoProducto": 1
    }
}

### Verificar Stock de Producto
- Metodo: GET
- URL: http://localhost:9000/productos/1

### Verificar Venta
- Metodo: GET
- URL: http://localhost:9000/ventas/1

---

## Evidencias de Pruebas

### Crear Cliente
POST http://localhost:9000/clientes

### Listar Clientes
GET http://localhost:9000/clientes

### Crear Producto
POST http://localhost:9000/productos

### Crear Usuario
POST http://localhost:9000/usuarios

### Crear Venta
POST http://localhost:9000/ventas

### Agregar Detalle
POST http://localhost:9000/detalles-venta

### Stock Actualizado
GET http://localhost:9000/productos/1

### Total Venta
GET http://localhost:9000/ventas/1

---

## Contacto y Soporte

Si tiene problemas o preguntas sobre la aplicacion, contacte al desarrollador:

- Nombre: Rodolfo Godinez
- GitHub: https://github.com/su-usuario

---

## Version

Version: 1.0.0
Fecha de lanzamiento: Enero 2025

---

## Licencia

Este software es propiedad de KinalApp. Todos los derechos reservados.

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

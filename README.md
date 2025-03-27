# M8 Práctica 2.

Leer con cuidado para poder realizar los puntos que se indican. Escoge 3
catálogos de tu base de datos para poder almacenar información introducida por
el usuario por medio de un formulario.

* Ocupa el proyecto de la práctica uno.
* Los formularios deben estar en las páginas que se indico en tu menú, por
ejemplo, Producto; debe indicar en el menú el enlace a la página producto
y hay estar el formulario.
* Evaluar si los datos que introduce el usuario son validos.
* Almacena la información introducida por el usuario en la tabla
correspondiente de tu base de datos.
* Se tiene que entregar el script de la base de datos y el proyecto dentro de
un solo archivo comprimido que se llame practica_dos.zip

## Tablas utilizadas para esta práctica
* Autor
* Editorial
* Clasificacion

| __autor__    | __editorial__ | __clasificacion__  |
|--------------|---------------|--------------------|
| id_autor     | id_editorial  | id_clasificacion   |
| nombre       | editorial     | tipo_clasificacion |
| apellido_1   |               |                    |
| apellido_2   |               |                    |
| nacionalidad |               |                    |

Para la __CREACIÓN y CARGA__ de la base de datos se tiene que usar el archivo:
> _adminlibrerias.sql_

Dicho archivo se encuetra en el ZIP de esta entrega.

## Ubicación de los formularios.

Los formularios se encontrarán en la sección de "Gestionar elementos" y ahí se podrán observar...

> Gestionar Autores → http://localhost:8080/user/manager/gestionar/autor
> 
> Gestionar Editoriales → http://localhost:8080/user/manager/gestionar/editorial
> 
> Gestionar Clasificaciones → http://localhost:8080/user/manager/gestionar/clasificacion

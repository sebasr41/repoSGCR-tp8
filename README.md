# Programación Visual - TP8: Validaciones - Filtros de búsquedas.

### Objetivos de aprendizaje

* Uso de Bean Validation.
* Creación de filtros de búsqueda

## Integrantes del grupo RCGS: 

| Apellido y  nombre:      | LU   | Usuario GitHub |
| ------------------------ | ---- | -------------- |
| Benicio Roxana           | 3215 | @3215U         |
| Rojas Rafael Sebastian   | 4292 | @sebasr41      |
| Michel Orlando Cristian  | 4155 | @mike47k       |
| Mamani Gabriel Alejandro | 4148 | @gam98         |

1. Continuando con el desarrollo del proyecto del TP N° 7, realice lo siguiente:

   Crear un nuevo repositorio donde se almacenará el proyecto del TP N° 8.
   En el archivo pom.xml agregue la siguiente dependencia:

   ```html
   <dependency>
   <groupId>org.hibernate.validator</groupId>
   <artifactId>hibernate-validator</artifactId>
   </dependency>
   ```

   Esta dependencia nos permite hacer uso de Bean Validation (JSR 380) que es una especificación de la API de Java para la validación de beans. Su uso nos permite imponer ciertas restricciones sobre los atributos de nuestras entidades utilizando anotaciones.

   **@NotNull** valida que el valor de la propiedad anotado no es nulo.
   **@Size** valida que el valor de la propiedad anotada tiene un tamaño entre los atributos min y max; se puede aplicar a las propiedades de cadena, colección , mapa y matriz.
   **@Min** valida que la propiedad anotada tiene un valor no menor que el atributo value.
   **@Max** valida que la propiedad anotada tiene un valor no mayor que el atributo value.
   **@Email** valida que la propiedad anotada es una dirección de correo electrónico válida.
   La propiedad message es común a todas las anotaciones y se refiere al mensaje que se presentará cuando el valor del atributo falle en la validación

   Las anotaciones mencionadas anteriormente perteneces al paquete *javax.validation.constraints*

   Otras anotaciones que se pueden encontrar en la especificación:

   **@NotEmpty** valida que la propiedad no sea nula ni esté vacía; se puede aplicar a valores de cadena, colección , mapa o matriz .
   **@NotBlank** se puede aplicar solo a valores de texto y valida que la propiedad no sea nula ni espacios en blanco.

2. Dentro del paquete **model** realice la agregación de las anotaciones necesarias para realizar la validación de los atributos.

   Para el caso de cadenas String utilice @NoEmpty, para las fechas @NotNull. Para valores numéricos @Min, @Max.

   Ejemplo:

   @NotEmpty(message = “El nombre no puede ser vacio”)
   @Size(min = 3, message = “El nombre no debe ser vacío, como mínimo debe tener 3 caracteres”) private String nombre;

   Luego realice las modificaciones en los controller y los archivos .html.

3. En la página donde se muestra la tabla de compras, agregue 2 componentes input para realizar la búsqueda de compras por nombre de producto y/o monto superior de la compra, el resultado de la búsqueda se debe mostrar en la tabla.

   Si no se ingresa nombre de producto se debe invocar al método que realiza la búsqueda solo por monto de la compra.

   En la capa repository debe crear los métodos para buscar por los 2 parámetros (nombreProducto, monto) y otro método que solo realice la búsqueda por monto (monto).

   


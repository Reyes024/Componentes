#Componente validador de datos y generador de historiales de busqueda.

##Descripción.
Este componenete de Java permite generar archivos .txt que permiten:
	- Generar una lista de elementos existentes y que es totalmente editable.
	- Generar una lista de elementos buscados a traves del buscador.
Este proyecto implementa un componente de búsqueda personalizado en Java Swing llamado `Buscador`. El `Buscador` incluye un campo de texto para ingresar el término de búsqueda, un botón con ícono para iniciar la búsqueda y una etiqueta para mostrar los resultados de la búsqueda.

#Caracteristicas.
1. **Campo de Texto (`JTextField`) con Texto Predeterminado:**
   - El campo de texto (`txtBuscar`) tiene un texto predeterminado "Buscar..." que se muestra en color gris.
   - Al hacer clic en el campo de texto, el texto predeterminado desaparece y el color del texto cambia a negro.
   - Si el campo de texto pierde el foco y está vacío, el texto predeterminado vuelve a aparecer.

2. **Botón de Búsqueda (`JButton`) con Ícono:**
   - El botón de búsqueda (`btn1`) muestra un ícono redimensionado que se carga desde el paquete de recursos.
   - El ícono cambia de tamaño al presionar y soltar el botón, proporcionando una retroalimentación visual al usuario.

3. **Manejo de Eventos del Botón:**
   - El botón de búsqueda está vinculado a un `ActionListener` que maneja la lógica de búsqueda cuando se presiona el botón.

4. **Separador (`JSeparator`):**
   - Un separador visual (`separator`) se coloca entre el campo de texto y la etiqueta de resultado para mejorar la estética del componente.

5. **Etiqueta de Resultados (`JLabel`):**
   - La etiqueta de resultados (`lblResultado`) muestra mensajes relacionados con la búsqueda, como resultados encontrados o errores.

6. **Lectura y Escritura de Archivos:**
   - El componente escribe el término de búsqueda en un archivo de historial de búsqueda (`historial de búsqueda.txt`).
   - El componente busca el término ingresado en un archivo de elementos de búsqueda (`elementos de búsqueda.txt`) y muestra si el término fue encontrado o no.

7. **Diseño con `GroupLayout`:**
   - El diseño del componente se gestiona utilizando un `GroupLayout`, que organiza el campo de texto, el botón, el separador y la etiqueta de resultado de manera ordenada.

8. **Manejo de Excepciones:**
   - Se implementa manejo de excepciones para asegurar que los errores durante la lectura y escritura de archivos se capturen y se notifiquen adecuadamente al usuario.

##Requisitos.
Para compilar y ejecutar este proyecto, asegúrate de tener lo siguiente:

### Software

1. **Java Development Kit (JDK) 8 o superior:**
   - [Descargar JDK](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)

2. **IDE para desarrollo en Java:**
   - [NetBeans](https://netbeans.apache.org/)
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
   - [Eclipse](https://www.eclipse.org/)


## API

La clase `Buscador` proporciona las siguientes funcionalidades principales:

### Métodos Públicos

| Método                            | Descripción                                                                 | Parámetros                              |
|-----------------------------------|-----------------------------------------------------------------------------|------------------------------------------|
| `public Buscador()`               | Inicializa una nueva instancia del componente `Buscador`.                   | Ninguno                                  |
| `public void actionPerformed(ActionEvent evt)` | Maneja la acción del botón de búsqueda. Este método se activa al presionar el botón de búsqueda. | `ActionEvent evt`: El evento de acción que dispara el método. |

### Métodos Privados

| Método                                                          | Descripción                                                                 | Parámetros                              |
|-----------------------------------------------------------------|-----------------------------------------------------------------------------|------------------------------------------|
| `private void escribirHistorialBusqueda(String textoBuscar) throws IOException` | Escribe el término de búsqueda en el archivo `historial de búsqueda.txt`.    | `String textoBuscar`: El término de búsqueda que se escribirá. |
| `private void buscarTextoEnArchivo(String textoBuscar) throws IOException`    | Busca el término de búsqueda en el archivo `elementos de búsqueda.txt` y actualiza la etiqueta de resultados (`lblResultado`) con el resultado. | `String textoBuscar`: El término de búsqueda que se buscará en el archivo de elementos. |

### Atributos

| Atributo                     | Descripción                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| `private JTextField txtBuscar`| Campo de texto donde el usuario ingresa el término de búsqueda.              |
| `private JButton btn1`       | Botón que inicia la búsqueda cuando es presionado.                           |
| `private JSeparator separator`| Separador visual entre el campo de texto y la etiqueta de resultados.        |
| `private JPanel innerPanel`  | Panel interno que contiene el campo de texto, el botón y el separador.       |
| `private JLabel lblResultado`| Etiqueta que muestra el resultado de la búsqueda.                             |
| `private Color defaultTextColor` | Color del texto predeterminado en el campo de búsqueda.                    |
| `private String defaultText` | Texto predeterminado en el campo de búsqueda.                                |
| `private int buttonWidth`    | Ancho del botón de búsqueda.                                                 |
| `private int buttonHeight`   | Alto del botón de búsqueda.                                                  |

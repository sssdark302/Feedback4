# FEEDBACK4 - AUGUSTO PERRONE

# repo git - https://github.com/sssdark302/Feedback4.git

### Funcionalidades Principales
1. **Gestión de Novelas**
    - **Añadir Novelas**: Los usuarios pueden agregar nuevas novelas proporcionando información como título, autor, género, número de páginas y descripción.
    - **Eliminar Novelas**: Las novelas se pueden eliminar desde la lista principal o a través de un menú contextual en cada ítem.
    - **Marcar como Favorita**: Permite marcar una novela como favorita para mostrarla en el widget y hacer un seguimiento de las preferencias del usuario.
      
2. **Visualización de la Lista de Novelas**
    - La lista de novelas está disponible en la actividad principal `ListaNovelasActivity` y permite ver todas las novelas añadidas.
    - Cada ítem de la lista muestra el título y el autor de la novela.
    - Al seleccionar una novela, se abre una vista detallada que muestra más información.

3. **Detalles de la Novela**
    - Al hacer clic en una novela desde la lista, se abre `DetallesNovelaActivity` con `FragmentDetallesNovelas`, que muestra información detallada sobre la novela seleccionada.
    - La interfaz de detalles también permite marcar o desmarcar la novela como favorita directamente.

4. **Widget**
    - La aplicación incluye un widget que muestra el título y el autor de la última novela marcada como favorita.
    - Dos botones permiten al usuario abrir los detalles de la novela favorita o acceder directamente a la lista completa de novelas.

5. **Navegación**
    - **NovelaManagerActivity**: Ofrece una interfaz para gestionar todas las novelas añadidas, con opciones de visualización y eliminación.
    - **Lista de Favoritas**: La aplicación también permite ver y gestionar novelas que han sido marcadas como favoritas.

### Estructura de la Aplicación
- **ListaNovelasActivity**: La actividad principal que muestra la lista de todas las novelas.
- **NovelaManagerActivity**: Actividad para gestionar (añadir y eliminar) novelas.
- **DetallesNovelaActivity**: Actividad que muestra los detalles de una novela específica.
- **Widget (NovelaWidgetProvider)**: Proporciona una vista rápida de la novela favorita y accesos directos para navegar por la aplicación.

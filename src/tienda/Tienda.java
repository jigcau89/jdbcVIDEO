package tienda;

import tienda.persistencia.ProductoDAO;
import tienda.servicios.FabricanteServicios;
import tienda.servicios.Menu;
import tienda.servicios.ProductoServicios;

/**
 *
 * @author jigcau89
 */
public class Tienda {

    public static void main(String[] args) throws Exception {

        FabricanteServicios fabricanteservicio = new FabricanteServicios();
        ProductoServicios productoservicio = new ProductoServicios();

         Menu.menu();
        try {
            //productoservicio.imprimirProductos();
        //  fabricanteservicio.crearFabricante("JOAQUIN");
        //    fabricanteservicio.crearFabricante(11, "Unionnnnn");
            
            //fabricanteservicio.eliminarFabricante(10);
         //   productoservicio.crearProducto("noteeebokkkk", 1989.2, 2);

        } catch (Exception ex) {

        }
    }

}

package tienda.servicios;

import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;
import tienda.servicios.ProductoServicios;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author jigcau89
 */
public class Menu {

    public static void menu() throws Exception {

        //  private ProductoDAO fafa = new productoD();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int selectorInt;
        String selectorString = "";

        try {

            do {
                do {
                    System.out.println("Ingrese un valor:"
                            + "\n1- Listar nombre de todos los productos."
                            + "\n2- Listar nombre y precio de todos los productos."
                            + "\n3- Listar productos con precio entre 120 y 202."
                            + "\n4- Listar todos los Portátiles. "
                            + "\n5- Listar nombre y precio del producto más barato. "
                            + "\n6- Ingresar un producto a la base de datos."
                            + "\n7- Ingresar un fabricante a la base de datos."
                            + "\n8- Editar un producto."
                            + "\n9- Salir");
                    selectorInt = leer.nextInt();
                } while (!(selectorInt > 0 && selectorInt < 10));

                switch (selectorInt) {
                    case 1:
                        System.out.println("LISTA DE PRODUCTOS - NOMBRE");
                        for (Producto p : ProductoDAO.listarProductosNombres()) {
                            System.out.println("Producto: " + p.getNombre());
                        }
//                       
                        break;
                    case 2:
                        System.out.println("LISTA DE PRODUCTOS - NOMBRE Y PRECIO");
                        for (Producto p : ProductoDAO.listarProductosNombres()) {
                            System.out.println("Producto: " + p.getNombre() + " Precio: " + p.getPrecio());
                        }
                        break;
                    case 3:
                        System.out.println("LISTA DE PRODUCTOS - PRECIO ENTRE 120 Y 202");
                        for (Producto p : ProductoDAO.listarProductosPrecio120y202()) {
                            System.out.println("Producto: " + p.getNombre() + " Precio: " + p.getPrecio());
                        }
                        break;
                    case 4:
                        System.out.println("LISTA DE PRODUCTOS - PORTATILES");
                        for (Producto p : ProductoDAO.listarProductosPortatiles()) {
                            System.out.println("Código: " + p.getCodigo() + " Nombre: " + p.getNombre() + " - Precio: $" + p.getPrecio() + " Código Fabricante: " + p.getCodigoFabricante());
                        }
                        break;
                    case 5:
                        System.out.println("LISTA DE PRODUCTOS - MAS BARATO");
                        for (Producto p : ProductoDAO.listarProductosMasBajo()) {
                            System.out.println("Producto: " + p.getNombre() + " Precio: " + p.getPrecio());
                        }
                        break;
                    case 6:
                        try {
                            System.out.println("INGRESO DE PRODUCTO A LA BASE DE DATOS");
                            ProductoServicios productoservicio = new ProductoServicios();

                            System.out.println("Ingrese nombre del producto");
                            String nombre = leer.next();
                            System.out.println("Ingrese precio del producto");
                            Double precio = leer.nextDouble();
                            System.out.println("Ingrese codigo del fabricante del producto");
                            int codigo_fabricante = leer.nextInt();
                            productoservicio.crearProducto(nombre, precio, codigo_fabricante);
                        } catch (Exception e) {
                            throw e;
                        }

                        break;
                    case 7:
                        try {
                            System.out.println("INGRESO DE FABRICANTE A LA BASE DE DATOS");
                            FabricanteServicios fabricanteservicio = new FabricanteServicios();
                            System.out.println("Ingrese nombre del fabricate");
                            String nombre = leer.next();
                                                        
                            fabricanteservicio.crearFabricante(nombre);

                        } catch (Exception e) {
                        }
                        break;
                    case 8:
                        try {
                            System.out.println("EDICION DE UN PRODUCTO");
                            System.out.println("Ingrese el nombre del producto a modificar");
                            String nombre = leer.next();
                            System.out.println("ingrese su precio actualizado");
                            Double precio = leer.nextDouble();

                            ProductoServicios productoservicio = new ProductoServicios();
                            //productoservicio.modificarProducto(selectorInt, nombre, precio, selectorInt);
                            
                        } catch (Exception e) {
                        }
                        break;
                    case 9:
                        System.out.println("Seguro que desea salir? Y/N");
                        selectorString = leer.next().toUpperCase();
                    default:
                        System.out.println("Gracias!. ---> visita www.jigcomp.com.ar.");
//                        LimpiarPantalla.limpiarConAviso();
                }

            } while (!selectorString.equalsIgnoreCase("Y"));

        } catch (Exception e) {
            throw e;
        }
    }
}

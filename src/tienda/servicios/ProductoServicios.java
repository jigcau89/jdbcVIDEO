package tienda.servicios;

import java.util.Collection;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author jigcau89
 */
public class ProductoServicios {

    private ProductoDAO daop;

    public ProductoServicios() {
        this.daop = new ProductoDAO();
    }

    public void crearProducto(String nombre, Double precio, Integer codigoFabricante) throws Exception {
        {
            try {
                //validamos

                if (nombre == null || nombre.trim().isEmpty()) {
                    throw new Exception("Debe indicar nombre del producto");
                }
                if (precio == null || precio.isNaN()) {
                    throw new Exception("Debe indicar precio del producto");
                }
                if (codigoFabricante == null || codigoFabricante.toString().trim().isEmpty()) {
                    throw new Exception("Debe indicar codigo del producto");
                }
//                if (buscarFabricantePorNombre(nombre) != null) {
//                    throw new Exception("Ya existe un fabricante con el nombre " + nombre);
//                }
                //creamos el producto
                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setCodigoFabricante(codigoFabricante);

                daop.guardarProducto(producto);

            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void modificarProducto(String nombre, Double precio) throws Exception {
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar el nombre del producto");
            }

            //buscamos
            Producto producto = buscarProductoPorNombre(nombre);
            
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            
            daop.modificarProducto(producto);
        } catch (Exception e) {
            throw e;

        }
    }

    public void eliminarProducto(Integer codigo) throws Exception {

        try {

            //Validamos 
            if (codigo < 0) {
                throw new Exception("Debe indicar el codigo");
            }
            daop.eliminarProducto(codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorCodigo(Integer codigo) throws Exception {
        try {
            //Validamos
            if (codigo < 0) {
                throw new Exception("Debe indicar el codigo");
            }
            Producto producto = daop.buscarProductoPorCodigo(codigo);
            //Verificamos
            if (producto == null) {
                throw new Exception("No se encontró el producto con el codigo indicado");
            }
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {

        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del producto");
            }
            Producto producto = daop.buscarProductoPorNombre(nombre);
            //Verificamos
            if (producto == null) {
                throw new Exception("No se encontró el producto con el nombre indicado");
            }
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {

        try {

            Collection<Producto> productos = daop.listarProductos();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {
        try {
            //Listamos los productos
            Collection<Producto> productos = listarProductos();

            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto p : productos) {
                    System.out.println(p.toString());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

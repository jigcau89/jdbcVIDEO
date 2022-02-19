package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.servicios.FabricanteServicios;

/**
 *
 * @author jigcau89
 */
public final class ProductoDAO extends DAO {

    private final FabricanteServicios fabricanteservice;

    public ProductoDAO() {
        this.fabricanteservice = new FabricanteServicios();
    }

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe ingresar un producto");

            }

            String sql = "INSERT INTO producto (nombre, precio, codigo_fabricante) "
                    + "VALUES ('" + producto.getNombre() + "'," + producto.getPrecio() + "," + producto.getCodigoFabricante() + ");";
            System.out.println("Producto " + producto.getNombre() + " agregado correctamente");
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public static void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto que desea modificar");
            }

            String sql = "UPDATE producto SET precio = " + producto.getPrecio() + " WHERE nombre = '" + producto.getNombre() + "';";
            
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void eliminarProducto(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM Product WHERE codigo = " + codigo + "";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Producto buscarProductoPorCodigo(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo = " + codigo + ";";
            consultarBase(sql);
            Producto producto = null;
            //?
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                Fabricante fabricante = fabricanteservice.buscarProductoPorCodigo(codigo);
                producto.setFabricante(fabricante);

                productos.add(producto);

            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
     public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre = " + nombre + ";";
            consultarBase(sql);
            Producto producto = null;
            //?
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                Fabricante fabricante = fabricanteservice.buscarProductoPorNombre(nombre);
                producto.setFabricante(fabricante);

                productos.add(producto);

            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM Producto;";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                Integer codigo = resultado.getInt(4);

                Fabricante fabricante = fabricanteservice.buscarFabricantePorCodigo(codigo);

                producto.setCodigoFabricante(resultado.getInt(4));
                producto.setFabricante(fabricante);
                productos.add(producto);

            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public static Collection<Producto> listarProductosNombres() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto;";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public static Collection<Producto> listarProductosPrecio120y202() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto WHERE precio BETWEEN 120 AND 202; ";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public static Collection<Producto> listarProductosPortatiles() throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre LIKE '%Portatil%'; ";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public static Collection<Producto> listarProductosMasBajo() throws Exception {
        try {
            String sql = "SELECT nombre, min(precio) as 'Precio' FROM Producto; ";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                producto = new Producto();

                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));

                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }
// public static Collection<Producto> listarProductosJOA() throws Exception {
//        try {
//            String sql = "SELECT nombre, precio FROM Producto ";
//            consultarBase(sql);
//            Producto producto = null;
//            Collection<Producto> productos = new ArrayList();
//
//            while (resultado.next()) {
//                 producto = new Producto();
//                producto.setCodigo(resultado.getInt(1));
//                producto.setNombre(resultado.getString(2));
//                producto.setPrecio(resultado.getDouble(3));
//                Integer codigo = resultado.getInt(4);
//                
////                Fabricante fabricante = fabricanteservice.buscarFabricantePorCodigo(codigo);
////
////                producto.setCodigoFabricante(resultado.getInt(4));
////                producto.setFabricante(fabricante);
//                productos.add(producto);
//            }
//            desconectarBase();
//            return productos;
//        } catch (Exception e) {
//            e.printStackTrace();
//            desconectarBase();
//            throw e;
//        }
//    }
}

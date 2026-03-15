package es.unican.is2.SegurosBusiness;
import es.unican.is2.SegurosCommon.Seguro;
import es.unican.is2.SegurosCommon.IGestionSeguros;
import es.unican.is2.SegurosCommon.IInfoSeguros;
import es.unican.is2.SegurosCommon.ISegurosDAO;
import es.unican.is2.SegurosCommon.Cliente;
import es.unican.is2.SegurosCommon.DataAccessException;
import es.unican.is2.SegurosCommon.IClientesDAO;
import es.unican.is2.SegurosCommon.IGestionClientes;
import es.unican.is2.SegurosCommon.OperacionNoValida;



public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

	private ISegurosDAO segurosDao;
	private IClientesDAO clientesDao;
	

	
	public GestionSeguros(IClientesDAO daoClientes, ISegurosDAO daoSeguros) {
		this.segurosDao = daoSeguros;
		this.clientesDao = daoClientes;
		}

	/**
	 * Agrega un nuevo seguro al cliente cuyo dni se indica.
	 * @param s Seguro que desea agregar
	 * @param dni DNI del cliente
	 * @return El seguro agregado
	 * 		   null si no se agrega porque no se encuentra el cliente
	 * @throws OperacionNoValida si el seguro ya existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */

	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = clientesDao.cliente(dni);
		if (cliente == null) {
			return null;
		}
		Seguro se = segurosDao.creaSeguro(s);
		if (se == null) {
			throw new OperacionNoValida("El seguro ya existe");
		}
		cliente.getSeguros().add(se);
		clientesDao.actualizaCliente(cliente);
		return se;
	}
	
	/**
	 * Elimina el seguro cuya matricula se indica y 
	 * que pertenece al cliente cuyo dni se indica
	 * @param matricula Identificador del seguro a eliminar
	 * @param dni DNI del propietario del seguro
 	 * @return El seguro eliminado
 	 *         null si el seguro o el cliente no existen
 	 * @throws OperacionNoValida si el seguro no pertenece al dni indicado
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
		
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException{
		Seguro seguro = segurosDao.seguroPorMatricula(matricula);
		Cliente cliente = clientesDao.cliente(dni);

		if (seguro == null || cliente == null) {
			return null;
		}
		if (cliente.getSeguros().contains(seguro) == false) {
			throw new OperacionNoValida("No se puede dar de baja el seguro con este id");
		}
		cliente.getSeguros().remove(seguro);
		seguro = segurosDao.eliminaSeguro(seguro.getId());
		clientesDao.actualizaCliente(cliente);
		return seguro;
}
	/**
	 * Agrega o modifica el conductor adicional al seguro cuya matricula se indica
	 * @param matricula Identificador del seguro
	 * @param conductor Nombre del conductor adicional a agregar
 	 * @return El seguro modificado
 	 *         null si el seguro no existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException{
		Seguro s = segurosDao.seguroPorMatricula(matricula);
		if (s == null) {
			return null;
		}
		s.setConductorAdicional(conductor);
		return segurosDao.actualizaSeguro(s);
		
	}
	public Cliente nuevoCliente(Cliente c) throws DataAccessException{
		return clientesDao.creaCliente(c);
	}
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
	    Cliente c = clientesDao.cliente(dni);
	    if (c == null) {
	        return null;
	    }
	    if (!c.getSeguros().isEmpty()) {
	        throw new OperacionNoValida("El cliente tiene seguros");
	    }
	    return clientesDao.eliminaCliente(dni);
	}
	@Override
	public Cliente cliente (String dni)  throws DataAccessException{
		Cliente c = clientesDao.cliente(dni);
		return c;
	}
	@Override
	public Seguro seguro (String matricula)  throws DataAccessException{
		Seguro s = segurosDao.seguroPorMatricula(matricula);
		return s;
	}
	
}

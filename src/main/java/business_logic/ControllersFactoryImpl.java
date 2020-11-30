package business_logic;

import repositories.DaosFactory;
import services.SessionServiceImpl;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private LoginController loginController;

	private TurnosController turnosController;

	private ClientesController clientesController;

	private VehiculosConOrdenDeTrabajoController vehiculosController;

	private OrdenesTrabajoController ordenesDeTrabajoController;

	private PresupuestosController presupuestosController;

	private RepuestosController repuestosController;

	private FacturasController facturasController;
	
	private EntregaDeVehiculoController entregasController;
	
	private SucursalesController sucursalesController;
	
	private PedidosController pedidosController;
	
	private VentasVehiculosController ventasController;
	
	public ControllersFactoryImpl(DaosFactory daos) {
		this.daos = daos;
	}

	@Override
	public LoginController makeLoginController() {
		if (loginController == null)
			loginController = new LoginController(daos.makeUsuariosDao(), SessionServiceImpl.getInstance());
		return loginController;
	}

	@Override
	public TurnosController makeTurnosController() {
		if (turnosController == null)
			turnosController = new TurnosController(daos.makeTurnosDao());
		return turnosController;
	}

	@Override
	public ClientesController makeClientesController() {
		if (clientesController == null)
			clientesController = new ClientesController(daos.makeClienteDao(), daos.makeDatosPersonalesDao());
		return clientesController;
	}

	@Override
	public VehiculosConOrdenDeTrabajoController makeVehiculosController() {
		if (vehiculosController == null) {
			this.vehiculosController = new VehiculosConOrdenDeTrabajoController(daos.makeVehiculoConOrdeDeTrabajoDao(),
					daos.makeOrdenDeTrabajoDao(), daos.makeFichaTecnicaVehiculoDao());
		}
		return this.vehiculosController;
	}

	@Override
	public OrdenesTrabajoController makeOrdenesDeTrabajoController() {
		if (ordenesDeTrabajoController == null)
			ordenesDeTrabajoController = new OrdenesTrabajoController(daos.makeOrdenDeTrabajoDao(),
					SessionServiceImpl.getInstance(), daos.makeFacturasDao(), daos.makePresupuestoDao());
		return ordenesDeTrabajoController;
	}

	@Override
	public PresupuestosController makePresupuestosController() {
		if (presupuestosController == null) {
			presupuestosController = new PresupuestosController(daos.makePresupuestoDao(),
					daos.makeTrabajosPlanificadosDao(), daos.makeRepuestosPlanificadosDao(), daos.makeRepuestoDao());	
		}
		return presupuestosController;
	}

	@Override
	public RepuestosController makeRepuestosController() {
		if (repuestosController == null)
			repuestosController = new RepuestosController(daos.makeRepuestoDao());
		return repuestosController;
	}

	@Override
	public FacturasController makeFacturasController() {
		if(facturasController == null) 
			facturasController = new FacturasController(daos);
		return facturasController;
	}
	
	@Override
	public EntregaDeVehiculoController makeEntregasController() {
		if(entregasController == null)
			entregasController = new EntregaDeVehiculoController(daos.makeClienteDao(), daos.makeDatosPersonalesDao(), daos.makeOrdenDeTrabajoDao(), daos.makePresupuestoDao(), daos.makeVehiculoConOrdeDeTrabajoDao(), daos.makeFichaTecnicaVehiculoDao(), daos.makeFacturasDao());
		return entregasController;
	}

	@Override
	public SucursalesController makeSucursalesController() {
		if(sucursalesController == null) {
			sucursalesController = new SucursalesController(daos.makeSucursalesDao());
		}
		return sucursalesController;
	}
	
	@Override
	public PedidosController makePedidosController() {
		if(pedidosController == null) {
			pedidosController = new PedidosController(daos.makeClienteDao(), daos.makeDatosPersonalesDao(), daos.makeFichaTecnicaVehiculoDao(), daos.makePedidoVehiculoDao());
		}
		return pedidosController;
	}

	@Override
	public VentasVehiculosController makeVentasVehiculosController() {
		if(ventasController == null) ventasController = new VentasVehiculosController(daos.makeVentaVehiculoDao());
		return ventasController;
	}
}

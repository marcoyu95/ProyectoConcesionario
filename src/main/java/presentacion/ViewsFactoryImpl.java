package presentacion;

import business_logic.ControllersFactory;
import presentacion.views.supervisor.ClientePresenter;
import presentacion.views.supervisor.SupervisorControlView;
import presentacion.views.tecnico.TecnicoControlView;

public class ViewsFactoryImpl extends ViewsFactory {

	private ControllersFactory controllers;

	public ViewsFactoryImpl(ControllersFactory factory) {
		controllers = factory;
		WorkbenchView.getInstance().addFrames(TecnicoControlView.getInstance());
		WorkbenchView.getInstance().addFrames(SupervisorControlView.getInstance());
	}

	@Override
	public Presenter makePresenter() {
		new TurnosPresenter(SupervisorControlView.getInstance().getTurnosView(),controllers.makeTurnosController());
		new ClientePresenter(SupervisorControlView.getInstance().getClientesView(),
				controllers.makeClientesController(), controllers.makeVehiculosController(),
				controllers.makeOrdenesDeTrabajoController());
		new OrdenDeTrabajoPresenter(controllers.makeOrdenesDeTrabajoController());
		new PresupuestosPresenter(controllers.makePresupuestosController(), controllers.makeRepuestosController(),
				controllers.makeOrdenesDeTrabajoController(), controllers.makeVehiculosController(),
				controllers.makeClientesController());
		new ConsultaDePresupuestoPresenter(controllers.makeVehiculosController(), controllers.makeClientesController(), controllers.makeOrdenesDeTrabajoController()
				,controllers.makePresupuestosController(), controllers.makeFacturasController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}

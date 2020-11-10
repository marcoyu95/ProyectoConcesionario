package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.EstadoPresupuesto;
import dto.PresupuestoDTO;
import repositories.PresupuestosDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class PresupuestosDaoImpl extends GenericJdbcDao<PresupuestoDTO> implements PresupuestosDao {
	
	private static final String readAll = "SELECT * FROM Presupuestos";

	private static final String readById = readAll + " " + "WHERE Presupuestos.idPresupuesto = ?";

	private static final String readByOrdenDeTrabajoId = readAll + " " + "WHERE Presupuestos.idOT = ?";

	private static final String insert = "INSERT INTO Presupuestos (idOT, idUsuAltaPresu, idUsuCierrePresu, idUsuRegPago"
			+ ",idPago,fechaAltaPresu,comentarioAltaPresu,fechaCierrePresu,comentarioCierrePresu,fechaAprobacion,estado) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	private static final String updateState = "UPDATE Presupuestos SET estado = ?, fechaAprobacion = ? WHERE idPresupuesto = ?";
	
	private static final String updateStateForPayment = "UPDATE Presupuestos SET estado = ? WHERE idPresupuesto = ?";
	
	public PresupuestosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean updateStateById(Integer id, Date aprovedDate, EstadoPresupuesto estado) {
			return getTemplate().query(updateState).param(estado.name()).param(aprovedDate).param(id).excecute();
	}
	
	@Override
	public boolean updateState(Integer id, EstadoPresupuesto estado) {
		return getTemplate().query(updateStateForPayment).param(estado.name()).param(id).excecute();
	}
	
	
	@Override
	public boolean update(PresupuestoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(PresupuestoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdOT())
				.param(entity.getIdUsuAltaPresu() == null ? new NullObject() : entity.getIdUsuAltaPresu())
				.param(entity.getIdUsuCierrePresu() == null ? new NullObject() : entity.getIdUsuCierrePresu())
				.param(entity.getIdUsuRegPago() == null ? new NullObject() : entity.getIdUsuRegPago())
				.param(entity.getIdPago() == null ? new NullObject() : entity.getIdPago())
				.param(entity.getFechaAltaPresu() == null ? new NullObject() : entity.getFechaAltaPresu())
				.param(entity.getComentarioAltaPresu() == null ? new NullObject() : entity.getComentarioAltaPresu())
				.param(entity.getFechaCierrePresu() == null ? new NullObject() : entity.getFechaCierrePresu())
				.param(entity.getComentarioCierrePresu() == null ? new NullObject() : entity.getComentarioCierrePresu())
				.param(entity.getFechaAprobacion() == null ? new NullObject() : entity.getFechaAprobacion())
				.param(entity.getEstado().name())				
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public PresupuestoDTO readByID(Integer id) {
		List<PresupuestoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<PresupuestoDTO> readByOrdenDeTrabajoId(Integer id) {
		return getTemplate().query(readByOrdenDeTrabajoId).param(id).excecute(getMapper());
	}

	@Override
	public List<PresupuestoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<PresupuestoDTO> getMapper() {
		return new Mapper<PresupuestoDTO>() {
		
			@Override
			public PresupuestoDTO map(Object[] obj) {
				PresupuestoDTO dto = new PresupuestoDTO();
				dto.setIdPresupuesto((Integer)obj[0]);
				dto.setIdOT((Integer) obj[1]);
				dto.setIdUsuAltaPresu(obj[2] == null ? null : (Integer) obj[2]);
				dto.setIdUsuCierrePresu(obj[3] == null ? null : (Integer) obj[3]);
				dto.setIdUsuRegPago(obj[4] == null ? null : (Integer) obj[4]);
				dto.setIdPago(obj[5] == null ? null : (Integer) obj[5]);
				dto.setFechaAltaPresu(obj[6] == null ? null : (Date) obj[6]);
				dto.setComentarioAltaPresu(obj[7] == null ? null : (String) obj[7]);
				dto.setFechaCierrePresu(obj[8] == null ? null : (Date) obj[8]);
				dto.setComentarioCierrePresu(obj[9] == null ? "" : (String) obj[9]);
				dto.setFechaAprobacion(obj[10] == null ? null : (Date) obj[10]);
				dto.setEstado((Enum.valueOf(EstadoPresupuesto.class, (String) obj[11])));
				return dto;
			}
		};
	}
}

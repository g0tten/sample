package gotten.momot;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import tcinputCloud.DataCentre;
import tcinputCloud.Rack;
import tcinputCloud.Board;
import gotten.utils.ModelManager;
import java.lang.Math;

public class FitnessFunctionForDatac_mr1 {
	private static String INITIAL_MODEL = null;
	private static double INITIAL_NNodes = 0.0;

	private static double getNNodesDynamic(EObject eObject, Set<EObject> processed) {
		double nnodes = 0.0;
		if (processed.contains(eObject) == false) {
			for (EAttribute att : eObject.eClass().getEAllAttributes()) {
				if (att.getName().equals("numBoards")) {
					Object value = ModelManager.getAttributeValue(att.getName(), eObject);
					if (value instanceof Integer) {
						if ((int) value != 0) {
							nnodes += (int) value;
						}
					}
					if (value instanceof Float) {
						if ((float) value != 0) {
							nnodes += (float) value;
						}
					}
					if (value instanceof Double) {
						if ((double) value != 0) {
							nnodes += (double) value;
						}
					}
				}
				if (att.getName().equals("nodesPerBoard")) {
					Object value = ModelManager.getAttributeValue(att.getName(), eObject);
					if (value instanceof Integer) {
						if ((int) value != 0) {
							nnodes += (int) value;
						}
					}
					if (value instanceof Float) {
						if ((float) value != 0) {
							nnodes += (float) value;
						}
					}
					if (value instanceof Double) {
						if ((double) value != 0) {
							nnodes += (double) value;
						}
					}
				}
			}
			for (EReference ref : eObject.eClass().getEAllReferences()) {
				if (ref.getName().equals("racks")) {
					Object o = ModelManager.getReferencedNoException(ref.getName(), eObject);
					if (o instanceof EObject) {
						EObject containedEObject = (EObject) o;
						if (processed.contains(containedEObject) == false) {
							double result = getNNodesDynamic(containedEObject, processed);
							if (result != 0) {
								nnodes += result;
							}
						}
					}
					if (o instanceof List<?>) {
						List<EObject> containedEObjects = (List<EObject>) o;
						for (EObject containedEObject : containedEObjects) {
							if (processed.contains(containedEObject) == false) {
								double result = getNNodesDynamic(containedEObject, processed);
								if (result != 0) {
									nnodes += result;
								}
							}
						}
					}
				}
				if (ref.getName().equals("board")) {
					Object o = ModelManager.getReferencedNoException(ref.getName(), eObject);
					if (o instanceof EObject) {
						EObject containedEObject = (EObject) o;
						if (processed.contains(containedEObject) == false) {
							double result = getNNodesDynamic(containedEObject, processed);
							if (result != 0) {
								nnodes += result;
							}
						}
					}
					if (o instanceof List<?>) {
						List<EObject> containedEObjects = (List<EObject>) o;
						for (EObject containedEObject : containedEObjects) {
							if (processed.contains(containedEObject) == false) {
								double result = getNNodesDynamic(containedEObject, processed);
								if (result != 0) {
									nnodes += result;
								}
							}
						}
					}
				}
			}
			processed.add(eObject);
		}
		return nnodes;
	}

	private static double getNNodes(DataCentre object) {
		double nnodes = 0.0;
		List<Rack> objectsRack = object.getRacks();
		for (Rack objectRack : objectsRack) {
			double result = getNNodes(objectRack);
			if (result != 0) {
				nnodes += result;
			}
		}
		return nnodes;
	}

	private static double getNNodes(Rack object) {
		double nnodes = 0.0;
		Object valuenumBoards = object.getNumBoards();
		if (valuenumBoards instanceof Integer) {
			if ((int) valuenumBoards != 0) {
				nnodes += (int) valuenumBoards;
			}
		}
		if (valuenumBoards instanceof Float) {
			if ((float) valuenumBoards != 0) {
				nnodes += (float) valuenumBoards;
			}
		}
		if (valuenumBoards instanceof Double) {
			if ((double) valuenumBoards != 0) {
				nnodes += (double) valuenumBoards;
			}
		}
		Board objectBoard = object.getBoard();
		double result = getNNodes(objectBoard);
		if (result != 0) {
			nnodes += result;
		}
		return nnodes;
	}

	private static double getNNodes(Board object) {
		double nnodes = 0.0;
		Object valuenodesPerBoard = object.getNodesPerBoard();
		if (valuenodesPerBoard instanceof Integer) {
			if ((int) valuenodesPerBoard != 0) {
				nnodes += (int) valuenodesPerBoard;
			}
		}
		if (valuenodesPerBoard instanceof Float) {
			if ((float) valuenodesPerBoard != 0) {
				nnodes += (float) valuenodesPerBoard;
			}
		}
		if (valuenodesPerBoard instanceof Double) {
			if ((double) valuenodesPerBoard != 0) {
				nnodes += (double) valuenodesPerBoard;
			}
		}
		return nnodes;
	}

	public static double getNNodesDistance(final DataCentre root, final String initial_model) {
		if (INITIAL_MODEL == null) {
			INITIAL_MODEL = initial_model;
			List<EPackage> packages = ModelManager.loadMetaModelNoException("model/datac.ecore");
			Resource resource = ModelManager.loadModelNoException(packages, initial_model);
			EObject initial_model_root = resource.getContents().get(0);
			Set<EObject> processed = new HashSet<EObject>();
			INITIAL_NNodes = getNNodesDynamic(initial_model_root, processed);
		}
		double nnodes = getNNodes(root);
		return Math.abs(INITIAL_NNodes - nnodes);
	}
}

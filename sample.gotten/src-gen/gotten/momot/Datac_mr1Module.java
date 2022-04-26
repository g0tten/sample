package gotten.momot;

public interface Datac_mr1Module {
	public static interface DecNumBoards {
		static interface Parameter {
			String X = DecNumBoards.NAME + Datac_mr1Module.SEPARATOR + "x";
		}

		static String NAME = Datac_mr1Module.MODULE + Datac_mr1Module.SEPARATOR + "decNumBoards";
	}

	public static interface DecNodesPerBoard {
		static interface Parameter {
			String X = DecNodesPerBoard.NAME + Datac_mr1Module.SEPARATOR + "x";
		}

		static String NAME = Datac_mr1Module.MODULE + Datac_mr1Module.SEPARATOR + "decNodesPerBoard";
	}

	static String SEPARATOR = "::";
	static String FILE_NAME = "datac_mr1";
	static String MODULE_NAME = "tcinputCloud";
	static String MODULE = FILE_NAME + SEPARATOR + MODULE_NAME;
}

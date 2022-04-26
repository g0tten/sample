package gotten.momot;

public interface Datac_mr2Module {
	public static interface DecCPUCores {
		static interface Parameter {
			String X = DecCPUCores.NAME + Datac_mr2Module.SEPARATOR + "x";
		}

		static String NAME = Datac_mr2Module.MODULE + Datac_mr2Module.SEPARATOR + "decCPUCores";
	}

	public static interface DecCPUSpeed {
		static interface Parameter {
			String X = DecCPUSpeed.NAME + Datac_mr2Module.SEPARATOR + "x";
		}

		static String NAME = Datac_mr2Module.MODULE + Datac_mr2Module.SEPARATOR + "decCPUSpeed";
	}

	static String SEPARATOR = "::";
	static String FILE_NAME = "datac_mr2";
	static String MODULE_NAME = "tcinputCloud";
	static String MODULE = FILE_NAME + SEPARATOR + MODULE_NAME;
}

package gotten.sample.gotten;

import gotten.CloudSim.CloudSimSampleMR1.CloudSimSampleMR1;
import gotten.CloudSim.CloudSimSampleMR2.CloudSimSampleMR2;
import gotten.Dissect.DissectSampleMR1.DissectSampleMR1;
import gotten.Dissect.DissectSampleMR2.DissectSampleMR2;
import java.util.Map;
import java.util.TreeMap;
import java.util.AbstractMap.SimpleEntry;

public class sample_gottenLauncher {
	private Map<String, Map<String, Map<String, SimpleEntry<Boolean, Boolean>>>> results = new TreeMap<String, Map<String, Map<String, SimpleEntry<Boolean, Boolean>>>>();

	public Map<String, Map<String, Map<String, SimpleEntry<Boolean, Boolean>>>> evaluateCloudSim() {
		results.put("CloudSim#MR1", CloudSimSampleMR1.evaluateMR1());
		results.put("CloudSim#MR2", CloudSimSampleMR2.evaluateMR2());
		return results;
	}

	public Map<String, Map<String, Map<String, SimpleEntry<Boolean, Boolean>>>> evaluateDissect() {
		results.put("Dissect#MR1", DissectSampleMR1.evaluateMR1());
		results.put("Dissect#MR2", DissectSampleMR2.evaluateMR2());
		return results;
	}
}

package edu.cmu.cs.relab.tortoise.datautility;
/*
 * Computes the utility score from a determined function derived from the 
 * confusion matrices and frequency averages.
 */
public class UtilityAvgEstimator extends Estimator {

	@Override
	public UtilityScore getScore(UtilityTarget target) {
		double score = 0.0;
		String[] types = target.getTypes();
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			switch (type) {
				case "Network Information": //names will have to match
					score = score + 0.692244433;
					break;
				case "IP addresses / Domain Names":
					score = score + 0.719129555;
					break;
				case "Packet Data":
					score = score + 0.474316802;
					break;
				case "Operating System (OS) Information":
					score = score + 0.646824393;
					break;
				case "OS Type and Version":
					score = score + 0.647393725;
					break;
				case "Usernames":
					score = score + 0.630630061;
					break;
				case "Passwords":
					score = score + 0.320786943;
					break;
				case "Running Process Information":
					score = score + 0.578188259;
					break;
				case "Registry Information":
					score = score + 0.503795547;
					break;
				case "Temporary Files":
					score = score + 0.474000506;
					break;
				case "Device Information":
					score = score + 0.586348684;
					break;
				case "Device Identifiers":
					score = score + 0.512209008;
					break;
				case "MAC Address":
					score = score + 0.490827429;
					break;
				case "UDID / IMEA":
					score = score + 0.233615891;
					break;
				case "Memory Data":
					score = score + 0.374430668;
					break;
				case "Sensor Data":
					score = score + 0.438132591;
					break;
				case "Application Information":
					score = score + 0.510184717;
					break;
				case "Browser History":
					score = score + 0.488360324;
					break;
				case "Keyword Searches":
					score = score + 0.38638664;
					break;
				case "Web Sites Visited":
					score = score + 0.510311235;
					break;
				case "Chat History":
					score = score + 0.271761134;
					break;
				case "Application Session Data":
					score = score + 0.336854757;
					break;
				case "E-mails":
					score = score + 0.486399291;
					break;
				case "Contact Information":
					score = score + 0.411690283;
					break;
				case "Keylogging Data":
					score = score + 0.211475202;
					break;
				case "Video / Image Files":
					score = score + 0.293965081;
					break;
				default:
					System.out.println("Invalid information type included");
					break;						
			}
		}
		return new UtilityScore(target, score);
	}

}

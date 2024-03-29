import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class Run {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		URL url = new URL("http://ricette.giallozafferano.it/Calamari-ripieni-di-couscous.html");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		int status = conn.getResponseCode();

		if (status >= 200 && status <= 299) {
			InputStreamReader in = new InputStreamReader(conn.getInputStream(), "UTF-8");

			StringBuilder sb = new StringBuilder();
			ParserDelegator delegator = new ParserDelegator();
			HTMLEditorKit.ParserCallback callback = new MyParserCallback(sb);
			delegator.parse(in, callback, false);

			System.out.println(
					"****** Preparazione calamari ripieni di couscous, ottenuta da http://ricette.giallozafferano.it/Calamari-ripieni-di-couscous.html ******\n");
			System.out.println(sb);
		}
	}

}

class MyParserCallback extends HTMLEditorKit.ParserCallback {

	private StringBuilder sb;

	public MyParserCallback(StringBuilder sb) {
		this.sb = sb;
	}

	public void handleText(char[] data, int pos) {
		// System.out.println("Stampo la posizione " + pos);
		// System.out.println(new String(data));
		if (pos >= 40735 && pos <= 45349)
			sb.append(data);
	}

}

package grafana.datasource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class JSONDataSource extends AbstractDataSource{
	private String url;
	public static final String SERVER = "proxy";
	public static final String BROWSER = "direct";
	
	public JSONDataSource(String name, String url, String access, long version, boolean editable) {
		setName(name);
		setUrl(url);
		setType("simpod-json-datasource");
		setAccess(access);
		setVersion(version);
		setEditable(editable);
	}
	
	public JSONDataSource(String name, String url, String access) {
		setName(name);
		setUrl(url);
		setType("simpod-json-datasource");
		setAccess(access);
		setVersion(System.currentTimeMillis());
		setEditable(false);
	}

	@Override
	public File createConfig(String grafanaPath) {
		File f = new File(grafanaPath+"\\conf\\provisioning\\datasources\\"+getName()+".yaml");
		String toFile = "apiVersion: 1\n"
				+ "datasources:\n"
				+ " - name: " + getName() + "\n"
				+ "   type: " + getType() + "\n"
				+ "   url: " + getUrl() + "\n"
				+ "   access: " + getAccess() + "\n"
				+ "   version: " + getVersion() + "\n"
				+ "   editable: " + isEditable();
		
		Writer writer = null;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(toFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

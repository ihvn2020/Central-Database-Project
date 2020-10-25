package ihvn.data.extractor.model.dao;

/**
 *
 * @author rsuth
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	
	private List<String> fileList;
	
	private static String SOURCE_FOLDER;
	
	public ZipUtil(String sourceFolder) {
		fileList = new ArrayList<String>();
		SOURCE_FOLDER = sourceFolder;
	}
	
	/*public static void main(String[] args) {
		ZipUtil appZip = new ZipUtil();
		appZip.generateFileList(new File(SOURCE_FOLDER));
		appZip.zipIt(OUTPUT_ZIP_FILE);
	}*/
	
	public void zipIt(String output_zipFile) {
		byte[] buffer = new byte[1024];
		String source = new File(SOURCE_FOLDER).getName();
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(output_zipFile);
			zos = new ZipOutputStream(fos);
			
			System.out.println("Output to Zip : " + output_zipFile);
			FileInputStream in = null;
			
			for (String file : this.fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				}
				finally {
					in.close();
				}
			}
			
			zos.closeEntry();
			System.out.println("Folder successfully compressed");
			
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				zos.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void generateFileList(File node) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString()));
		}
		
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}
	}
	
	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
        
        
        
        public static void zipFolder(String zippedFilePath, String folderPath)
        {
            try {
                
                File file = new File(folderPath);
                
                String [] srcFiles = file.list();//Arrays.asList("test1.txt", "test2.txt");
                FileOutputStream fos = new FileOutputStream(zippedFilePath);
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (String srcFile : srcFiles) {
                    File fileToZip = new File(folderPath+File.separator+srcFile);
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);
                    
                    byte[] bytes = new byte[1024];
                    int length;
                    while((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                    fis.close();
                    //System.out.println("currently zipping "+srcFile);
                }
                zipOut.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
}

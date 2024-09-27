/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import java.util.Date;
import java.text.SimpleDateFormat;
import static javafx.scene.input.KeyCode.I;

/**
 *
 * @author JASRAJ
 */
public class IDutil {

    public static String generateProdId() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String prodId = sdf.format(today);
        prodId = "P" + prodId;
        return prodId;
        
    }
    
public static String generateTransld() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String transld = sdf.format(today);
        transld = "T" + transld;
        return transld;
    }
}

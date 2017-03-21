package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;

/**
 * Created by teemu on 17.3.2017.
 */
@Repository
@Data
public class Fragment {

    public String line1;
    public String line2;
    public String hintLine;
}

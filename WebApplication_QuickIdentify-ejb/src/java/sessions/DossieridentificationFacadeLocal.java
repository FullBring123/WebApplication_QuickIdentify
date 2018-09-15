/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Dossieridentification;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Windows8.1
 */
@Local
public interface DossieridentificationFacadeLocal {

    void create(Dossieridentification dossieridentification);

    void edit(Dossieridentification dossieridentification);

    void remove(Dossieridentification dossieridentification);

    Dossieridentification find(Object id);

    List<Dossieridentification> findAll();

    List<Dossieridentification> findRange(int[] range);

    int count();
    
    Integer nextId();
    
}

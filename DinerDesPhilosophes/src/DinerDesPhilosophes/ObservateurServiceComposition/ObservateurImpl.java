/*====================================================================

This file was produced by the OpenCCM CIF_JIMPL generator.

OpenCCM: The Open CORBA Component Model Platform
Copyright (C) 2000-2004 INRIA & USTL - LIFL - GOAL
Contact: openccm@objectweb.org

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
USA

Initial developer(s): Christophe Demarey.
Contributor(s): ______________________________________.

====================================================================*/

package DinerDesPhilosophes.ObservateurServiceComposition;


/**
 *  This is the CIDL-based implementation of the
 *  OMG IDL3 IDL:DinerDesPhilosophes/Observateur:1.0 component type.
 * 
 *  @author OpenCCM CIF_Jimpl Compiler.
 */
public class ObservateurImpl
     extends DinerDesPhilosophes.ObservateurServiceComposition.ObservateurService_impl
{
    // ==================================================================
    //
    // Internal states.
    //
    // ==================================================================
    
    // ENIS: Entrepot pour les états des philosophes
    private java.util.Hashtable _etats_philosophes;

    // ENIS: Fenetre principale
    private javax.swing.JFrame _fenetre;

    // ENIS: Entrepot de panels
    private java.util.Hashtable _philosophePanels;

    // ENIS: boite pour contenit l'interface graphique d'un philosophe
    private javax.swing.Box _boite;

    // ==================================================================
    //
    // Constructors.
    //
    // ==================================================================
    
    public ObservateurImpl()
    {
	_etats_philosophes = new java.util.Hashtable();
        _philosophePanels = new java.util.Hashtable();
    }
    
    // ==================================================================
    //
    // Methods.
    //
    // ==================================================================
    
    /**
     * Implementation of the ::DinerDesPhilosophes::CCM_InfoEtatConsumer::push operation.
     */
    public void
    push(DinerDesPhilosophes.InfoEtat event) 
    {
	// ENIS: Mise à jour du panel

	synchronized (this) {
	    if (! _philosophePanels.containsKey(event.nom)) {
		PhilosophePanel panel = new PhilosophePanel(event);
		_philosophePanels.put(event.nom, panel);
		_boite.add(panel);
		_fenetre.pack();
		_fenetre.show();
	    } else {
		PhilosophePanel panel;
		panel = (PhilosophePanel)_philosophePanels.get(event.nom);
		panel.majPanel(event);
	    }
	}
    }
    
    /**
     *  Complete the component configuration.
     *  
     *  @exception org.omg.Components.InvalidConfiguration
     *             Thrown if the configuration is invalid.
     */
    public void
    configuration_complete() 
    throws org.omg.Components.InvalidConfiguration
    {
        // ENIS: Instanciation de l'interface graphique

	_fenetre = new javax.swing.JFrame("Observateur");
        _fenetre.setSize(400, 300);
        _boite = javax.swing.Box.createVerticalBox();
        _fenetre.getContentPane().add(_boite);
	_fenetre.setLocation (400, 400);
        _fenetre.pack();
        _fenetre.show();
    }
    
    /**
     *  Sets the session component context.
     *  
     *  @param context The session component context.
     *  
     *  @throw org.omg.Components.CCMException For any problems.
     */
    public void
    set_session_context(org.omg.Components.SessionContext context) 
    throws org.omg.Components.CCMException
    {
        super.set_session_context(context);
    }
    
    /**
     *  Container callback to signal that the component is activated.
     *  
     *  @throw org.omg.Components.CCMException For any problems.
     */
    public void
    ccm_activate() 
    throws org.omg.Components.CCMException
    {
        super.ccm_activate();
    }
    
    /**
     *  Container callback to signal that the component is passivated.
     *  
     *  @throw org.omg.Components.CCMException For any problems.
     */
    public void
    ccm_passivate() 
    throws org.omg.Components.CCMException
    {
        super.ccm_passivate();
    }
    
    /**
     *  Container callback to signal that the component is removed.
     *  
     *  @throw org.omg.Components.CCMException For any problems.
     */
    public void
    ccm_remove() 
    throws org.omg.Components.CCMException
    {
        // ENIS: DEstruction de la fenetre
        _fenetre.dispose();
        _fenetre = null;
    }
    
}

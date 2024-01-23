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

package DinerDesPhilosophes.FourchetteManagerSessionComposition;


/**
 *  This is the CIDL-based implementation of the
 *  OMG IDL3 IDL:DinerDesPhilosophes/FourchetteManager:1.0 component type.
 * 
 *  @author OpenCCM CIF_Jimpl Compiler.
 */
public class FourchetteManagerImpl
     extends DinerDesPhilosophes.FourchetteManagerSessionComposition.FourchetteManagerSession_impl
{
    // ==================================================================
    //
    // Internal states.
    //
    // ==================================================================
    
    // ENIS: Etat interne de la fourchette

    private boolean _libre;

    // ==================================================================
    //
    // Constructors.
    //
    // ==================================================================
    
    public FourchetteManagerImpl()
    {
	// ENIS: Au début, la fourchette est libre
	_libre = true;
	
    }
    
    // ==================================================================
    //
    // Methods.
    //
    // ==================================================================
    
    /**
     * Implementation of the ::DinerDesPhilosophes::Fourchette::prendre operation.
     */
    public void
    prendre() 
    throws DinerDesPhilosophes.Utilisee
    {
        // ENIS: On ne prend la fourchette que si elle est
        // libre. Sinon, on lève une exception.

	if (!_libre) {
	    throw new  DinerDesPhilosophes.Utilisee ();
	}

	_libre = false;
    }
    
    /**
     * Implementation of the ::DinerDesPhilosophes::Fourchette::liberer operation.
     */
    public void
    liberer() 
    {
	//  ENIS: On marque la fourchette comm étant libre
	_libre = true;
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
        super.configuration_complete();
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
        super.ccm_remove();
    }
    
}

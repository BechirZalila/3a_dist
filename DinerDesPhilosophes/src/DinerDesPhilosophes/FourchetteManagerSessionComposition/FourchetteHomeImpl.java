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
 *  This is the CIDL-based implementation of 
 *  OMG IDL3 IDL:DinerDesPhilosophes/FourchetteHome:1.0 home type.
 * 
 *  This class inherits from the generated home executor skeleton class
 *  defined by OMG CIDL DinerDesPhilosophes.FourchetteManagerSessionComposition.FourchetteManagerHome_impl
 * 
 *  @author OpenCCM CIF_Jimpl Compiler.
 */
public class FourchetteHomeImpl
     extends DinerDesPhilosophes.FourchetteManagerSessionComposition.FourchetteManagerHome_impl
{
    // ==================================================================
    //
    // Internal states.
    //
    // ==================================================================
    
    // ==================================================================
    //
    // Constructors.
    //
    // ==================================================================
    
    public FourchetteHomeImpl()
    {
    }
    
    // ==================================================================
    //
    // Methods.
    //
    // ==================================================================
    
    /**
     *  Method for the org.objectweb.ccm.runtime.cif.api.SegmentHome interface.
     *  
     *  Create an executor segment from its identifier.
     *  
     *  @param segid - The executor segment identifier.
     *  
     *  @return - The executor segment created.
     */
    public org.omg.Components.ExecutorSegmentBase
    create_executor_segment(int segid) 
    {
        switch (segid)
        {
            default :
                return new DinerDesPhilosophes.FourchetteManagerSessionComposition.FourchetteManagerImpl();
        }
    }
    
    /**
     *  This method is called by the OpenCCM Component Server
     *  to create a home instance.
     *  
     *  @return - The created home.
     */
    public static org.omg.Components.HomeExecutorBase
    create_home() 
    {
            return new DinerDesPhilosophes.FourchetteManagerSessionComposition.FourchetteHomeImpl();
    }
    
}

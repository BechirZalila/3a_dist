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

package DinerDesPhilosophes;


/**
 *  Implementation skeleton class for the IDL:DinerDesPhilosophes/InfoEtat:1.0 declaration.
 * 
 *  @author OpenCCM CIF_Jimpl Compiler.
 */
public class InfoEtatImpl
     extends InfoEtat
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
    
    public InfoEtatImpl()
    {
        nom = "";
        etat = EtatPhilosophe.MORT;
        ticks_depuis_dernier_repas = 0;
        a_fourchette_gauche = false;
        a_fourchette_droite = false;
    }

    public InfoEtatImpl(EtatPhilosophe e,
                   String n,
                   int ticks,
                   boolean g,
                   boolean r)
    {
        etat = e;
        nom = n;
        ticks_depuis_dernier_repas = ticks;
        a_fourchette_gauche = g;
        a_fourchette_droite = r;
    }
    
    // ==================================================================
    //
    // Methods.
    //
    // ==================================================================
    
}

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

package DinerDesPhilosophes.PhilosopheSessionComposition;

import DinerDesPhilosophes.*;

/**
 *  This is the CIDL-based implementation of the
 *  OMG IDL3 IDL:DinerDesPhilosophes/Philosophe:1.0 component type.
 * 
 *  ENIS: Cette classe impléménte l'interface Runnable pour pouvoir
 *  exécuter le comportement d'un philosophe dans un thread
 *  indépendant.
 *
 *  @author OpenCCM CIF_Jimpl Compiler.
 */
public class PhilosopheImpl
    extends DinerDesPhilosophes.PhilosopheSessionComposition.PhilosopheSession_impl
    implements java.lang.Runnable
{
    // ==================================================================
    //
    // Internal states.
    //
    // ==================================================================
    
    // ENIS: Le nom du philosophe
    private String _nom;

    // ENIS: Le nombre de ticks depuis l'initialisation du philosophe
    private int _duree_ticks = 0;

    // ENIS: Compteur pour mesurer l'état de famine du philosophe
    private int _famine;

    // ENIS: COmpteur pour savoir depuis cobien de ticks un philosophe
    // possède une fourchette tout en restant affamé
    private int _famine_count;

    // ENIS: Possède la fourchette gauche
    private boolean _fourchette_gauche;

    // ENIS: Possède la fourchette droite
    private boolean _fourchette_droite;

    // ENIS: Machine à état contenant le comportement du philosophe
    private java.lang.Thread _comportement;

    // ENIS: Etat courant du philosophe
    private EtatPhilosophe _etat;

    // ENIS: Fenetre du philosophe
    private javax.swing.JFrame _fenetre;

    // ENIS: Icone bouton du philosophe
    private javax.swing.JButton _bouton;

    // ENIS: Duree de chaque état (mange, réfléchit, a_faim, affamé et
    // mort).
    private int[] dureeEtats = {-1, 3, 10, 40, -1};

    // ENIS: Icone pour représenter l'état du philosophe
    private javax.swing.ImageIcon[] iconeEtat;

    // ==================================================================
    //
    // Constructors.
    //
    // ==================================================================
    
    public PhilosopheImpl()
    {
	// ENIS: Faire le reste de l'initialisation sans initialiser
	// le nom.
	this (null);
    }

    // ENIS: Un second sonstructeur pour initialiser le nom du
    // philosophe.

    public PhilosopheImpl(String nom)
    {
	_nom = nom;
	
	java.util.Random rand =
            new java.util.Random(System.currentTimeMillis());
        _duree_ticks = 1000 + rand.nextInt(2010);
        _fourchette_gauche = false;
        _fourchette_droite = false;
        _etat = null;
        _comportement = null;
        _famine_count = 0;
        _famine = 0;
	
        // Creer l'icone du philosophe
        iconeEtat = new javax.swing.ImageIcon[5];
        java.net.URL url = PhilosopheImpl.class.getResource("/images/mange.gif");
        iconeEtat[0] = new javax.swing.ImageIcon(url);
        url = PhilosopheImpl.class.getResource("/images/reflechit.gif");
        iconeEtat[1] = new javax.swing.ImageIcon(url);
        url = PhilosopheImpl.class.getResource("/images/a_faim.gif");
        iconeEtat[2] = new javax.swing.ImageIcon(url);
        url = PhilosopheImpl.class.getResource("/images/a_faim_droite.gif");
        iconeEtat[3] = new javax.swing.ImageIcon(url);
        url = PhilosopheImpl.class.getResource("/images/mort.jpg");
        iconeEtat[4] = new javax.swing.ImageIcon(url);
    }
    
    // ==================================================================
    //
    // Methods.
    //
    // ==================================================================
    
    /**
     * Implementation of the ::DinerDesPhilosophes::CCM_Philosophe_Executor::nom attribute as accessor operation.
     */
    public String
    nom() 
    {
        // ENIS: Retourner le nom du philosophe
        return _nom;
    }
    
    /**
     * Implementation of the ::DinerDesPhilosophes::CCM_Philosophe_Executor::nom attribute as mutator operation.
     */
    public void
    nom(String nom) 
    {
        // ENIS: Modification du nom du philosophe
	_nom = nom;
    }
    
    /**
     * ENIS: effectue le travail nécessaire pour l'état courant
     */
    protected void
    tickSuivant()
    {
        switch(_etat.value())
        {
        case EtatPhilosophe._MANGE :
            {
                if (_famine > 0)
		    {
			// Entre dans l'état
			setEtat(EtatPhilosophe.MANGE);
			// Mange...
			_famine-=3;
		    } else 
		    {
			_famine = 0;
			// Libère la fourchette gauche
			if (_fourchette_gauche)
			    {
				get_context().get_connection_gauche().liberer();
				_fourchette_gauche = false;
			    }
			// Libère la fourchette droite
			if (_fourchette_droite)
			    {
				get_context().get_connection_droite().liberer();
				_fourchette_droite = false;
			    }
			// Réflechit
			setEtat(EtatPhilosophe.REFLECHIT);
		    }
                break;
            }
        case EtatPhilosophe._REFLECHIT :
            {
                if (_famine < dureeEtats[EtatPhilosophe._REFLECHIT])
		    {
			setEtat(EtatPhilosophe.REFLECHIT);
		    } else 
		    {
			setEtat(EtatPhilosophe.A_FAIM);
		    }
                break;
            }
        case EtatPhilosophe._A_FAIM :
            {
                // Essaie de prendre la fourchette gauche
		if (! _fourchette_gauche)
		    {
			try {
			    get_context().get_connection_gauche().prendre();
			    _fourchette_gauche = true;
			    _famine_count = 0;
			} catch(Utilisee exc) {
			    if (_fourchette_droite && _famine_count >= 5) {
				// Liberer la fourchette droite
				get_context().get_connection_droite().liberer();
				_fourchette_droite = false;
				_famine_count = 0;
			    }
			}
		    }
		
		// Essaie de prendre la fourchette droite
		if (! _fourchette_droite)
		    {
			try {
			    get_context().get_connection_droite().prendre();
			    _fourchette_droite = true;
			    _famine_count = 0;
			} catch(Utilisee exc) {
			    if (_fourchette_gauche && _famine_count >= 5) {
				// Liberer la fourchette gauche
				get_context().get_connection_gauche().liberer();
				_fourchette_gauche = false;
				_famine_count = 0;
			    }
			}
		    }
		
		if (_fourchette_gauche && _fourchette_droite) {
		    _famine_count = 0;
		    // Entrer dans l'état MANGE
		    setEtat(EtatPhilosophe.MANGE);
		} else if (_famine >= dureeEtats[EtatPhilosophe._A_FAIM]) 
		    {
			_famine_count = 0;
			// Entrer dabs l'état AFFAME
			setEtat(EtatPhilosophe.AFFAME);
		    } else 
		    {
			_famine_count++;
			// Entrer dans l'état A_FAIM
			setEtat(EtatPhilosophe.A_FAIM);
		    }
                break;
            }
        case EtatPhilosophe._AFFAME :
            {
                // Essaie de prendre la fourchette gauche
		if (! _fourchette_gauche)
		    {
			try {
			    get_context().get_connection_gauche().prendre();
			    _fourchette_gauche = true;
			} catch(Utilisee exc) {
			}
		    }
		
		// Essaie de prendre la fourchette droite
		if (! _fourchette_droite)
		    {
			try {
			    get_context().get_connection_droite().prendre();
			    _fourchette_droite = true;
			} catch(Utilisee exc) {
			}
		    }
		
		if (_fourchette_gauche && _fourchette_droite) {
		    // Enterer dans l'etat MANGER
		    setEtat(EtatPhilosophe.MANGE);
		} else if (_famine < dureeEtats[EtatPhilosophe._AFFAME]) {
		    // Entrer dans l'etat AFFAME
		    setEtat(EtatPhilosophe.AFFAME);
		} else {
		    // Entrer dans l'etat MORT
		    setEtat(EtatPhilosophe.MORT);
		}
                break;
            }
        case EtatPhilosophe._MORT :
            {
		// Liberer la fourchette gauche
		if (_fourchette_gauche)
		    {
			get_context().get_connection_gauche().liberer();
			_fourchette_gauche = false;
		    }
		// Liberer la fourchette droite
		if (_fourchette_droite)
		    {
			get_context().get_connection_droite().liberer();
			_fourchette_droite = false;
		    }
		// Entrer dans l'etat MORT
		setEtat(EtatPhilosophe.MORT);
                break;
            }
        }

        // ENIS: Attendre le tick suivant
        try {
            _comportement.sleep(_duree_ticks);
        } catch(java.lang.InterruptedException exc) {
            exc.printStackTrace();
        }

        // ENIS: Voir si le compteur de famine doit être incrémenté
        switch (_etat.value())
        {
        case EtatPhilosophe._REFLECHIT :
        case EtatPhilosophe._A_FAIM :
        case EtatPhilosophe._AFFAME :
            {
                _famine++;
                break;
            }
        }

        // ENIS: Appel récursif à la même fonction
        tickSuivant();
    }

    /**
     * ENIS: Change l'état du philosophe et envoie un événement à
     * l'observateur.
     */
    protected void
    setEtat(EtatPhilosophe etat)
    {
        _etat = etat;

        _bouton.setIcon(iconeEtat[_etat.value()]);

        // ENIS: Envoie un événement à l'observateur
	get_context().push_info(new InfoEtatImpl(_etat, _nom,
						 _famine, _fourchette_gauche,
						 _fourchette_droite));
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
        // ENIS: Actions invoqué à la fin de la configuration de
        // l'application
	
	// Vérifier si la configuration est effectivement complétée
        if((_nom == null)||
	   ((_duree_ticks == 0)||
	    ((get_context().get_connection_gauche() == null)||
	     (get_context().get_connection_droite() == null))))
            throw new org.omg.Components.InvalidConfiguration();
	
        // Créer l'interface graphique
        _fenetre = new javax.swing.JFrame(_nom + " GUI");
        javax.swing.JPanel bord = new javax.swing.JPanel
	    (new java.awt.BorderLayout());
	
        // Créer le bouton
        _bouton = new javax.swing.JButton();
        _bouton.setIcon(iconeEtat[_etat._REFLECHIT]);
	
        // Label
        javax.swing.JLabel label = new javax.swing.JLabel
	    ("Bonjour! Je suis " + _nom + " !!");
        label.setFont(label.getFont().deriveFont((float)16.0));
	
        // Organisation
        bord.add(_bouton, java.awt.BorderLayout.CENTER);
        bord.add(label, java.awt.BorderLayout.SOUTH);
        _fenetre.getContentPane().add(bord);
	
	// Positionnement du philosophe dans un endroit aléatoire de l'écran
	java.util.Random rand =
            new java.util.Random(System.currentTimeMillis());
	int x = rand.nextInt (1000);
	int y = rand.nextInt (700);
	
        // Affichage
	_fenetre.setLocation (x, y);
        _fenetre.pack();
        _fenetre.show();
	
        // Début du comportement
        _comportement = new java.lang.Thread(this);
        _comportement.start();
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
        // ENIS: Destruction de la fenêtre
        _fenetre.dispose();
        _fenetre = null;

        // ENIS: Arrêter le comportement du philosophe
        _comportement.stop();
        _comportement = null;
    }

    /**
     * ENIS: COmportement du philosophe
     */
    public void
    run()
    {
        // ENIS: The philosopher commence par réflechir
        setEtat(EtatPhilosophe.REFLECHIT);
        tickSuivant();
    }
    
}

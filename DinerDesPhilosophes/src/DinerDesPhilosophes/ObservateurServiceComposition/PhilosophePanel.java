package DinerDesPhilosophes.ObservateurServiceComposition;

import DinerDesPhilosophes.*;

/**
 * Interface graphique montrant l'état du philosophe tel qu'il est vu
 * par l'observateur.
 */

public class PhilosophePanel 
    extends javax.swing.JPanel 
{

    protected javax.swing.JProgressBar _famine;
    protected javax.swing.JCheckBox _fourchette_droite;
    protected javax.swing.JCheckBox _fourchette_gauche;
    protected javax.swing.JLabel _nom;
    protected javax.swing.JLabel _etat;
    protected String[] _chaines_etat = {
        "MANGE", "REFLECHIT", "A_FAIM", "AFFAME", "MORT"
    };

     /**
     * Initialise le panel en utilisant un évènement entrant
     */ 
    public PhilosophePanel(InfoEtat etat) 
    {
        super(new java.awt.BorderLayout());

        java.net.URL url;
        javax.swing.JPanel faminePanel = new javax.swing.JPanel
	    (new java.awt.BorderLayout());
        javax.swing.JPanel famineSousPanel = new javax.swing.JPanel
	    (new java.awt.FlowLayout());
        java.awt.Font font = getFont();
        font = font.deriveFont((float)16.0);

	// Barre de progression au centre
        _famine = new javax.swing.JProgressBar(0, 40);
        _famine.setSize(new java.awt.Dimension(150, 30));
        famineSousPanel.add(_famine);
        faminePanel.add(new javax.swing.JLabel(" "), 
			java.awt.BorderLayout.NORTH);
        faminePanel.add(famineSousPanel, 
			java.awt.BorderLayout.CENTER);

	// Nom du Philosophe
        _nom = new javax.swing.JLabel(etat.nom);
        _nom.setPreferredSize(new java.awt.Dimension(100, _nom.getHeight()));
        _nom.setFont(font);

	// Les fourchettes
        _fourchette_droite = new javax.swing.JCheckBox();
        _fourchette_gauche = new javax.swing.JCheckBox();

        // Label pouyr l'état
        _etat = new javax.swing.JLabel();
        _etat.setPreferredSize(new java.awt.Dimension(100, _etat.getHeight()));
        _etat.setFont(font);

	// Icones des fourchettes
        url = PhilosophePanel.class.getResource("/images/fourchette.gif");
        _fourchette_droite.setSelectedIcon(new javax.swing.ImageIcon(url));
        _fourchette_gauche.setSelectedIcon(new javax.swing.ImageIcon(url));
        url = PhilosophePanel.class.getResource("/images/pasdefourchette.gif");
        _fourchette_droite.setIcon(new javax.swing.ImageIcon(url));
        _fourchette_gauche.setIcon(new javax.swing.ImageIcon(url));

        add(_nom, java.awt.BorderLayout.WEST);

        javax.swing.JPanel fourchettes = new javax.swing.JPanel
	    (new java.awt.BorderLayout());
        fourchettes.add(_fourchette_droite, java.awt.BorderLayout.EAST);
        fourchettes.add(_etat, java.awt.BorderLayout.CENTER);
        fourchettes.add(_fourchette_gauche, java.awt.BorderLayout.WEST);
        
        add(fourchettes, java.awt.BorderLayout.CENTER);
        add(faminePanel, java.awt.BorderLayout.EAST);

	// Initialisation
        majPanel(etat);
        setBorder(javax.swing.BorderFactory.createBevelBorder
		  (javax.swing.border.BevelBorder.RAISED));
        setVisible(true);
    }

    protected java.awt.Color getFG(int ticks)
    {
        if (ticks < 3) return java.awt.Color.green;
        if (ticks < 10) return java.awt.Color.yellow;
        if (ticks < 40) return java.awt.Color.orange;
        return java.awt.Color.red;
    }

    public void majPanel(InfoEtat e)
    {
        _etat.setText(_chaines_etat[e.etat.value()]);
	_famine.setForeground(getFG(e.ticks_depuis_dernier_repas));
        _famine.setValue(e.ticks_depuis_dernier_repas);
        _fourchette_droite.setSelected(e.a_fourchette_gauche);
        _fourchette_gauche.setSelected(e.a_fourchette_droite);
    }
}


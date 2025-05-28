import React from 'react';
import Header from './components/Header';
import HeroSection from './components/HeroSection';
import OrganizationSection from './components/OrganizationSection';
import styles from './styles/Home.module.css';

export default function Home() {
  return (
    <main>
      <Header />
      <HeroSection />
      <OrganizationSection />
      
      {/* Opening Plenary Section */}
      <section className={styles.plenarySection}>
        <div className="container">
          <h2 className={styles.sectionTitle}>OPENING PLENARY</h2>

          <div className={styles.plenaryBox}>
            <h3 className={styles.plenaryTitle}>
              Advanced control strategies for sustainable energy systems in complex networks.
            </h3>
            <p className={styles.plenarySpeaker}>
              Prof. Maria Rodriguez. Institute of Technology, University of Cambridge, Cambridge, UK
            </p>
          </div>
        </div>
      </section>
    </main>
  );
}
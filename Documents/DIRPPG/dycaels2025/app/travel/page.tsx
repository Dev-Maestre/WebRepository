import React from 'react';
import Header from '../components/Header';
import HeroSection from '../components/HeroSection';
import OrganizationSection from '../components/OrganizationSection';
import styles from '../styles/ContentSection.module.css';

export default function Goals() {
  return (
    <main>
      <Header />
      <HeroSection />
      <OrganizationSection />
      
      {/* Goals Section */}
      <section className={styles.contentSection}>
        <div className="container">
          <h2 className={styles.sectionTitle}>TRAVEL INFORMATIONS</h2>

          <div className={styles.contentBox}>
            <h3 className={styles.contentTitle}>
              Conference Objectives
            </h3>
            
            <p className={styles.contentText}>
              The main goals of DYCAELS 2025 are:
            </p>
            
            <ul className={styles.list}>
              <li className={styles.listItem}>
                To provide a premier interdisciplinary platform for researchers, practitioners, and educators
              </li>
              <li className={styles.listItem}>
                To foster collaboration between researchers working in theoretical aspects and practical applications
              </li>
              <li className={styles.listItem}>
                To promote the exchange of ideas in energy production and renewable energy sources
              </li>
              <li className={styles.listItem}>
                To explore new research directions in dynamics and control
              </li>
              <li className={styles.listItem}>
                To provide opportunities for young researchers to present their work
              </li>
              <li className={styles.listItem}>
                To strengthen international cooperation among researchers
              </li>
            </ul>
            
            <p className={styles.contentText}>
              We aim to create a vibrant environment for scientific exchange and networking that will lead to new collaborations and research initiatives.
            </p>
          </div>
        </div>
      </section>
    </main>
  );
}
import React from 'react';
import Header from '../components/Header';
import HeroSection from '../components/HeroSection';
import OrganizationSection from '../components/OrganizationSection';
import styles from '../styles/ContentSection.module.css';

export default function Topics() {
  return (
    <main>
      <Header />
      <HeroSection />
      <OrganizationSection />
      
      {/* Topics Section */}
      <section className={styles.contentSection}>
        <div className="container">
          <h2 className={styles.sectionTitle}>TOPICS</h2>

          <div className={styles.contentBox}>
            <h3 className={styles.contentTitle}>
              Conference Topics
            </h3>
            
            <p className={styles.contentText}>
              DYCAELS 2025 welcomes submissions in the following areas:
            </p>
            
            <ul className={styles.list}>
              <li className={styles.listItem}>
                Nonlinear Dynamics and Chaos
              </li>
              <li className={styles.listItem}>
                Control Theory and Applications
              </li>
              <li className={styles.listItem}>
                Vibration Analysis and Control
              </li>
              <li className={styles.listItem}>
                Energy Harvesting Systems
              </li>
              <li className={styles.listItem}>
                Renewable Energy Sources
              </li>
              <li className={styles.listItem}>
                Biomechanics and Biomedical Engineering
              </li>
              <li className={styles.listItem}>
                Robotics and Mechatronics
              </li>
              <li className={styles.listItem}>
                Smart Materials and Structures
              </li>
              <li className={styles.listItem}>
                Computational Methods in Dynamics
              </li>
              <li className={styles.listItem}>
                Applications in Life Sciences
              </li>
            </ul>
            
            <p className={styles.contentText}>
              Other related topics may be considered. Please contact the organizing committee if you have questions about the suitability of your research.
            </p>
          </div>
        </div>
      </section>
    </main>
  );
}
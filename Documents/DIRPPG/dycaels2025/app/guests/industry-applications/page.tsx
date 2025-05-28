import React from 'react';
import Header from '../../components/Header';
import HeroSection from '../../components/HeroSection';
import OrganizationSection from '../../components/OrganizationSection';
import styles from '../../styles/ContentSection.module.css';

export default function KeynoteSpeakers() {
  return (
    <main>
      <Header />
      <HeroSection />
      <OrganizationSection />
      
      {/* Keynote Speakers Section */}
      <section className={styles.contentSection}>
        <div className="container">
          <h2 className={styles.sectionTitle}>INDUSTRY APPLICATIONS</h2>

          <div className={styles.contentBox}>
            <h3 className={styles.contentTitle}>
              Distinguished Keynote Speakers for DYCAELS 2025
            </h3>
            
            <div className={styles.speakerList}>
              <div className={styles.speakerItem}>
                <h4 className={styles.speakerName}>Prof. Maria Rodriguez</h4>
                <p className={styles.speakerAffiliation}>Institute of Technology, University of Cambridge, UK</p>
                <p className={styles.speakerTopic}>
                  "Advanced control strategies for sustainable energy systems in complex networks"
                </p>
                <p className={styles.speakerBio}>
                  Prof. Rodriguez is a leading researcher in control theory with over 20 years of experience in developing 
                  innovative control strategies for complex systems. Her work has been published in top journals and she has 
                  received numerous awards for her contributions to the field.
                </p>
              </div>
              
              <div className={styles.speakerItem}>
                <h4 className={styles.speakerName}>Prof. John Smith</h4>
                <p className={styles.speakerAffiliation}>Stanford University, USA</p>
                <p className={styles.speakerTopic}>
                  "Nonlinear dynamics in mechanical systems: Theory and applications"
                </p>
                <p className={styles.speakerBio}>
                  Prof. Smith's research focuses on nonlinear dynamics and chaos theory with applications in mechanical 
                  engineering. He has authored several textbooks on the subject and is a fellow of the American Society 
                  of Mechanical Engineers.
                </p>
              </div>
              
              <div className={styles.speakerItem}>
                <h4 className={styles.speakerName}>Dr. Sophia Chen</h4>
                <p className={styles.speakerAffiliation}>Tokyo Institute of Technology, Japan</p>
                <p className={styles.speakerTopic}>
                  "Energy harvesting from ambient vibrations: Recent advances and future directions"
                </p>
                <p className={styles.speakerBio}>
                  Dr. Chen is a pioneer in the field of energy harvesting from mechanical vibrations. Her innovative 
                  designs have led to significant improvements in the efficiency of energy harvesting devices and have 
                  been implemented in various industrial applications.
                </p>
              </div>
            </div>
            
            <p className={styles.contentText}>
              More keynote speakers will be announced soon. Please check back for updates.
            </p>
          </div>
        </div>
      </section>
    </main>
  );
}
import React from 'react';
import Header from '../components/Header';
import HeroSection from '../components/HeroSection';
import OrganizationSection from '../components/OrganizationSection';
import styles from '../styles/ContentSection.module.css';

export default function Program() {
  return (
    <main>
      <Header />
      <HeroSection />
      <OrganizationSection />
      
      {/* Final Program Section */}
      <section className={styles.contentSection}>
        <div className="container">
          <h2 className={styles.sectionTitle}>FINAL PROGRAM</h2>

          <div className={styles.contentBox}>
            <h3 className={styles.contentTitle}>
              Conference Schedule - November 10-15, 2025
            </h3>
            
            <p className={styles.contentText}>
              Below is the preliminary schedule for DYCAELS 2025. The detailed program will be updated closer to the event date.
            </p>
            
            <table className={styles.table}>
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Main Activities</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>November 10</td>
                  <td>Registration, Opening Ceremony, Plenary Session</td>
                </tr>
                <tr>
                  <td>November 11</td>
                  <td>Plenary Sessions, Parallel Sessions on Nonlinear Dynamics</td>
                </tr>
                <tr>
                  <td>November 12</td>
                  <td>Parallel Sessions on Control Systems, Poster Session</td>
                </tr>
                <tr>
                  <td>November 13</td>
                  <td>Parallel Sessions on Applied Engineering, Workshop Sessions</td>
                </tr>
                <tr>
                  <td>November 14</td>
                  <td>Parallel Sessions on Life Science, Conference Dinner</td>
                </tr>
                <tr>
                  <td>November 15</td>
                  <td>Closing Ceremony, Technical Visits</td>
                </tr>
              </tbody>
            </table>
            
            <p className={styles.contentText}>
              A detailed schedule with specific times, speakers, and locations will be published in September 2025.
            </p>
          </div>
        </div>
      </section>
    </main>
  );
}
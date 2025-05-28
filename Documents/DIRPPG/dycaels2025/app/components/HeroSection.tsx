import React from 'react';
import styles from '../styles/Home.module.css';

export default function HeroSection() {
  return (
    <section className={styles.heroSection}>
      <div className={styles.heroBackground}></div>
      <div className={`container ${styles.heroContent}`}>
        <h2 className={styles.heroTitle}>DYCAELS 2025</h2>

        <div className={styles.registrationBox}>
          <p className={styles.onlineText}>ONLINE AND IN-PERSON</p>
          <p className={styles.registrationText}>REGISTRATION</p>
          <p className={styles.dateText}>NOVEMBER 10 - 15, 2025</p>
        </div>

        <div className={styles.conferenceBox}>
          <p className={styles.conferenceText}>
            V CONFERENCE ON DYNAMICS, CONTROL, AND APPLICATIONS TO APPLIED ENGINEERING AND LIFE SCIENCE AND
          </p>
          <p className={styles.conferenceText}>
            II WORKSHOP ON NONLINEAR DYNAMICS, ENERGY PRODUCTION, RENEWABLE ENERGY SOURCES, ENERGY TRANSFER AND ENERGY
            HARVESTING
          </p>
        </div>
      </div>
    </section>
  );
}
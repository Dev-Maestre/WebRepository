import React from 'react';
import styles from '../styles/Home.module.css';

export default function OrganizationSection() {
  return (
    <section className={styles.organizationSection}>
      <div className="container">
        <div className={styles.organizationBox}>
          <p className={styles.organizationText}>
            Organization: J. M. Balthazar, A. M. Tusset, Carlos Henrique Illa Font, Mauricio A. Ribeiro, Fernanda
            Correa, Juliana M. T. de Abreu Pietrobelli, Claudia Tania Picinin (UTFPR-Ponta Grossa), Juliana Simonović
            (University of Nis, Serbia) and Paulo Batista Gonçalves, (Pontifical Catholic University of Rio de
            Janeiro-PUCRJ) and G. Litak and P. Wolszczak, (Department of Automation Lublin University of Technology,
            Lublin, Poland)
          </p>
        </div>
      </div>
    </section>
  );
}
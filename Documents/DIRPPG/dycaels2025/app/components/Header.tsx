'use client';
import React, { useState, useRef, useEffect } from 'react';
import Link from 'next/link';
import styles from '../styles/Home.module.css';

export default function Header() {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);

  // Fechar o dropdown quando clicar fora dele
  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsDropdownOpen(false);
      }
    }

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <header className={styles.header}>
      <div className={`container ${styles.headerContainer}`}>
        <div className={styles.logoContainer}>
          <Link href="/" className={styles.logoLink}>
            <div className={styles.logo}>D25</div>
            <h1 className={styles.siteName}>DYCAELS</h1>
          </Link>
        </div>

        <nav className={styles.nav}>
          <Link href="/program" className={styles.navLink}>Program</Link>
          <Link href="/goals" className={styles.navLink}>Goals</Link>
          <Link href="/topics" className={styles.navLink}>Topics</Link>
          
          {/* Dropdown para Guests */}
          <div className={styles.dropdownContainer} ref={dropdownRef}>
            <button 
              className={`${styles.navLink} ${styles.dropdownButton}`} 
              onClick={toggleDropdown}
              aria-expanded={isDropdownOpen}
            >
              Guests ▾
            </button>
            {isDropdownOpen && (
              <div className={styles.dropdownMenu}>
                <Link href="/guests/keynote-speakers" className={styles.dropdownItem}>
                  Keynote Speakers
                </Link>
                <Link href="/guests/invited-speakers" className={styles.dropdownItem}>
                  Invited Speakers
                </Link>
                <Link href="/guests/industry-applications" className={styles.dropdownItem}>
                  Industry Applications
                </Link>
                <Link href="/guests/transportation-machinery" className={styles.dropdownItem}>
                  Transportation Machinery
                </Link>
                <Link href="/guests/energy-keynotes" className={styles.dropdownItem}>
                  Energy Keynotes Lecture
                </Link>
              </div>
            )}
          </div>
          
          <Link href="/special-issues" className={styles.navLink}>Issues</Link>
          <Link href="/mini-symposium" className={styles.navLink}>Symposium</Link>
          <Link href="/book-series" className={styles.navLink}>Books</Link>
          <Link href="/submit" className={styles.navLink}>Submit</Link>
          <Link href="/travel" className={styles.navLink}>Travel</Link>
        </nav>
      </div>
    </header>
  );
}
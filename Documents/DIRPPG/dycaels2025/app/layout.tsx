import './globals.css';
import type { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'DYCAELS 2025 - Dynamics, Control, and Applications to Engineering and Life Science',
  description: 'V Conference on Dynamics, Control, and Applications to Applied Engineering and Life Science',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
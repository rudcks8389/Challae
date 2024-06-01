import React from 'react';
import { Link } from 'react-router-dom';
import styles from './PageTemplate.module.css';

const CommonComponent = () => {
    return (
        <>
            <header className={styles.header}>
                <div className={styles.logo}>CHAL-LAE ADMIN PAGE</div>
                <div className={styles["user-info"]}>
                    <span>관리자님 로그인중!</span>
                    <button>Logout</button>
                </div>
            </header>
            <aside className={styles.sidebar}>
                <nav>
                    <ul>
                        <li><Link to='/'>홈</Link></li>
                        <li><Link to='/dashboard'>대시보드</Link></li>
                        <li><Link to='/member'>멤버 관리</Link></li>
                        <li><Link to='/team'>팀 승인</Link></li>
                    </ul>
                </nav>
            </aside>
            </>
    );
}

export default CommonComponent;

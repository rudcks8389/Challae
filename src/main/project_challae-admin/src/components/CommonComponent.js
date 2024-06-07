import React from 'react';
import { Link } from 'react-router-dom';
import styles from './PageTemplate.module.css';
import Swal from 'sweetalert2';

const CommonComponent = () => {
    const handleLogout = () => {
        Swal.fire({
            title: '관리모드를 종료 하시겠습니까?',
            text: "CHAL-LAE사이트로 이동합니다.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '이동하기',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                // 로컬호스트로 리디렉션
                window.location.href = 'http://192.168.0.5:80';
            }
        });
    };
    return (
        <>
            <header className={styles.header}>
                <div className={styles.logo}>CHAL-LAE ADMIN PAGE</div>
                <div className={styles["user-info"]}>
                    <span>관리자님 로그인중!</span>
                    <button onClick={handleLogout}>종료하기</button>
                </div>
            </header>
            <aside className={styles.sidebar}>
                <nav>
                    <ul>
                        <li><Link to='/'>홈</Link></li>
                        <li><Link to='/dashboard'>대시보드</Link></li>
                        <li><Link to='/member'>멤버 관리</Link></li>
                        <li><Link to='/club'>클럽 목록</Link></li>
                        <li><Link to='/team'>팀 승인</Link></li>
                    </ul>
                </nav>
            </aside>
            </>
    );
}

export default CommonComponent;

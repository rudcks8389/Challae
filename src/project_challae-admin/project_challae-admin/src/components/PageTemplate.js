import styles from './PageTemplate.module.css';
import { Link, Outlet } from "react-router-dom";

const PageTemplate = () => {
    return (
        <div className={styles.container}>
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

            <main className={styles["main-content"]}>
                <section className={styles.content}>
                <h1 className={styles.title}>CHAL-LAE 관리자님 환영합니다!</h1>
            <p>관리자모드가 제공하는 기능입니다.</p>

            <div className={styles.stats}>
                <div className={styles["stat-card"]}>
                    <h3>대시보드</h3>
                    <h5>사이트 현황 보기</h5>
                    <h5>회원수/클럽수/게시글수 보기</h5>
                   
                </div>
                <div className={styles["stat-card"]}>
                    <h3>멤버 관리</h3>
                    <h5>모든 회원 보기 및 퇴출기능</h5>
                    
                </div>
                <div className={styles["stat-card"]}>
                    <h3>팀 승인</h3>
                    <h5>팀 생성승인 요청에 대한 승인/거절</h5>
                  
                </div>
            </div>
                    <Outlet />
                    
                </section>
            </main>
        </div>
    );
}

export default PageTemplate;

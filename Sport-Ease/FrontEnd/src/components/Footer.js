import React from 'react'
import './Footer.css'

const Footer = () => {
  return (
      <>
      <footer className="site-footer">
      <div className="container">
        <div className="row">
          <div className="col-sm-12 col-md-6">
            <h6>Cities we are in:</h6>
            <p className="text-justify"><strong>
            <ul>
            <li>Pune</li>
            <li>Mumbai</li>
            <li>Bangalore</li>
            <li>Delhi</li>
            <li>Kolkata</li>
            <li>Nashik</li>
            </ul></strong></p>
          </div>
          <div className="col-xs-6 col-md-3">
            <h6>Sports</h6>
            <ul className="footer-links">
              <li><strong>Cricket</strong></li>
              <li><strong>Football</strong></li>
              <li><strong>Badminton</strong></li>
              <li><strong>Tennis</strong></li>
              <li><strong>Table Tennis</strong></li>
            </ul>
          </div>

          <div className="col-xs-6 col-md-3">
            <h6>Help & Support</h6>
            <ul className="footer-links">
              <li><strong><a href="http://scanfcode.com/about/">FAQs</a></strong></li>
              <li><strong><a href="http://scanfcode.com/contact/">Privacy Policy</a></strong></li>
              <li><strong><a href="http://scanfcode.com/contribute-at-scanfcode/">Terms & Conditions</a></strong></li>
              <li><strong><a href="http://scanfcode.com/privacy-policy/">Pricing & Refunds</a></strong></li>
              <li><strong><a href="http://scanfcode.com/sitemap/">Sitemap</a></strong></li>
            </ul>
          </div>
        </div>
        <hr/>
      </div>
      <div className="container">
        <div className="row">
          <div className="col-md-8 col-sm-6 col-xs-12">
            <p className="copyright-text">Copyright &copy; 2023 All Rights Reserved by 
         <a href="#"> SportEase</a>.
            </p>
          </div>
        </div>
      </div>
</footer>
</>
  )
}

export default Footer
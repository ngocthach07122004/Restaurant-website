import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from "leaflet";
import axios from "axios";
import "leaflet/dist/leaflet.css";
import "./styles.scss";

const IMAGE_URL = [
  "https://images.alphacoders.com/438/thumb-1920-438979.jpg",
  "https://c8.alamy.com/comp/CB61G6/china-yunnan-dali-old-town-at-night-CB61G6.jpg",
  "https://th.bing.com/th/id/OIF.cLCaC8lynYPCOZHrYk4hGQ?w=254&h=183&c=7&r=0&o=5&dpr=1.3&pid=1.7",
  "https://c8.alamy.com/comp/2T3CR68/restaurant-in-old-wooden-house-shaxi-yunnan-china-2T3CR68.jpg",
];

const BranchMap = () => {
  const navigate = useNavigate();
  const [branches, setBranches] = useState([]);

  useEffect(() => {
    // Fetch branch data from the API
    axios
      .get("http://localhost:8080/chinhanh/all")
      .then((response) => {
        setBranches(response.data);
      })
      .catch((error) => {
        console.error("Error fetching branch data:", error);
      });
  }, []);

  // Generate random coordinates within Ho Chi Minh City bounds
  const getRandomCoordinates = () => {
    const latMin = 10.762622;
    const latMax = 10.823099;
    const lngMin = 106.660172;
    const lngMax = 106.682231;
    const lat = Math.random() * (latMax - latMin) + latMin;
    const lng = Math.random() * (lngMax - lngMin) + lngMin;
    return [lat, lng];
  };

  const handleApply = (branchId, branchName) => {
    navigate("/careers", {
      state: { branchId, branchName },
    });
  };

  return (
    <MapContainer
      center={[10.762622, 106.660172]}
      zoom={12}
      style={{ height: "600px", width: "100%" }}
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      />
      {branches.map((branch, index) => (
        <Marker
          key={branch.maChiNhanh}
          position={[
            branch.latitude || 10.762622,
            branch.longitude || 106.660172,
          ]} // Fallback to default coordinates
          icon={L.icon({
            iconUrl: "https://leafletjs.com/examples/custom-icons/leaf-red.png",
            iconSize: [38, 95],
            iconAnchor: [22, 94],
            popupAnchor: [-3, -76],
            shadowUrl:
              "https://leafletjs.com/examples/custom-icons/leaf-shadow.png",
            shadowSize: [50, 64],
            shadowAnchor: [4, 62],
          })}
        >
          <Popup>
            <div>
              <strong>{branch.tenChiNhanh}</strong>
              <br />
              <img
                src={branch.imageUrl || IMAGE_URL[index % IMAGE_URL.length]}
                alt={`Image of ${branch.tenChiNhanh}`}
                style={{ width: "100%", height: "auto", marginBottom: "10px" }}
              />
              <p>
                <strong>Address:</strong> {branch.diaChi}
              </p>
              <p>
                <strong>Description:</strong> {branch.moTa}
              </p>
              <p>
                <strong>Status:</strong> {branch.trangThaiHoatDong}
              </p>
              <p>
                <strong>Open:</strong>{" "}
                {new Date(branch.thoiGianMoCua).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                })}
              </p>
              <p>
                <strong>Close:</strong>{" "}
                {new Date(branch.thoiGianDongCua).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                })}
              </p>
              <div>
                <button
                  onClick={() => navigate("/menu")}
                  className="btn btn-danger"
                >
                  Đặt món
                </button>
                <button
                  onClick={() =>
                    handleApply(branch.maChiNhanh, branch.tenChiNhanh)
                  }
                  className="ms-3 btn btn-danger"
                >
                  Apply
                </button>
              </div>
            </div>
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default BranchMap;

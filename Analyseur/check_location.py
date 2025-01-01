from flask import Flask, request, jsonify
from geopy.distance import geodesic

app = Flask(__name__)

@app.route('/check-location', methods=['POST'])
def check_location():
    try:
        # Récupérer les données du body
        data = request.json
        location = data.get('location')  # Exemple: [latitude, longitude]
        safe_zone = data.get('safeZone')  # Exemple: {"center": [latitude, longitude], "radius": 5}
        
        if not location or not safe_zone:
            return jsonify({"error": "location and safeZone are required"}), 400

        # Extraire le centre et le rayon de la zone de sécurité
        center = safe_zone.get('center')  # Exemple: [latitude, longitude]
        radius = safe_zone.get('radius')  # Rayon en kilomètres

        if not center or radius is None:
            return jsonify({"error": "safeZone must include 'center' and 'radius'"}), 400

        # Calculer la distance entre la localisation et le centre
        distance = geodesic(location, center).km
        warning = distance > radius

        # Retourner la réponse
        return jsonify({
            "location": location,
            "safeZone": safe_zone,
            "distance": distance,
            "warning": warning,
            "message": "Out of safe zone" if warning else "Within safe zone"
        }
        
        )
    
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(port=5000, debug=True)

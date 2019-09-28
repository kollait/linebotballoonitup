/*
 * Copyright 2019 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.bot.model.response;

import static java.util.Collections.emptyList;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = GetFriendsDemographicsResponse.GetFriendsDemographicsResponseBuilder.class)
public class GetFriendsDemographicsResponse {

    @Value
    @Builder
    @JsonDeserialize(builder = GenderTile.GenderTileBuilder.class)
    static class GenderTile {
        /**
         * Gender string. Possible values: "male", "female", "unknown".
         */
        String gender;

        /**
         * Percentage. Possible values: 0, 2.9, 37.6 etc.
         */
        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class GenderTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = AgeTile.AgeTileBuilder.class)
    static class AgeTile {
        /**
         * Age string. Possible values: "from50", "from0to14", "unknown" etc.
         */
        String age;

        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AgeTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = AreaTile.AreaTileBuilder.class)
    static class AreaTile {
        /**
         * Area. The value returned depends on the country.
         * Possible values[JP]: "北海道", "青森" etc.
         * Possible values[TW]: "台北市", "新北市" etc.
         * Possible values[TH]: "Bangkok", "Pattaya" etc.
         */
        String area;

        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AreaTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = AppTypeTile.AppTypeTileBuilder.class)
    static class AppTypeTile {
        /**
         * Operating System. Possible values: "ios", "android", "others".
         */
        String appType;

        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AppTypeTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = SubscriptionPeriodTile.SubscriptionPeriodTileBuilder.class)
    static class SubscriptionPeriodTile {
        /**
         * Subscription period. Possible values: "within7days", "within90days", "unknown" etc.
         */
        String subscriptionPeriod;

        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class SubscriptionPeriodTileBuilder {
            // Filled by lombok
        }
    }

    /**
     * true if friend demographic information is available.
     */
    boolean available;

    /**
     * Percentage per gender.
     */
    @Default
    List<GenderTile> genders = emptyList();

    /**
     * Percentage per age group.
     */
    @Default
    List<AgeTile> ages = emptyList();

    /**
     * Percentage per area.
     */
    @Default
    List<AreaTile> areas = emptyList();

    /**
     * Percentage by OS.
     */
    @Default
    List<AppTypeTile> appTypes = emptyList();

    /**
     * Percentage per friendship duration.
     */
    @Default
    List<SubscriptionPeriodTile> subscriptionPeriods = emptyList();

    @JsonPOJOBuilder(withPrefix = "")
    public static class GetFriendsDemographicsResponseBuilder {
        // Filled by lombok
    }
}
